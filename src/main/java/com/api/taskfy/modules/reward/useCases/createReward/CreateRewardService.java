package com.api.taskfy.modules.reward.useCases.createReward;

import com.api.taskfy.errors.ranking.RankingExpiredException;
import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.ranking.repositories.UserPlacementRepository;
import com.api.taskfy.modules.reward.dtos.CreateRewardDto;
import com.api.taskfy.modules.reward.entities.Reward;
import com.api.taskfy.modules.reward.repositories.RewardRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateRewardService {
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;


    public void execute(String userId, CreateRewardDto createRewardDto) {
        var rankingFound = this.rankingRepository.findById(createRewardDto.rankingId);

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        var ranking = rankingFound.get();

        if (LocalDateTime.now().isAfter(ranking.getDueDate())) {
            throw new RankingExpiredException();
        }

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, ranking.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER &&
                taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        var newReward = new Reward(createRewardDto);

        var rewardList = this.rewardRepository.findAllByRankingIdOrderByPositionAsc(createRewardDto.rankingId);

        if (rewardList.isEmpty()) {
            newReward.setPosition(1);
        } else {
            var lastReward = rewardList.getLast();

            newReward.setPosition(lastReward.getPosition() + 1);
        }

        this.rewardRepository.save(newReward);
    }
}
