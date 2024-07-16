package com.api.taskfy.modules.reward.useCases.updateReward;

import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.reward.RewardNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.reward.dtos.UpdateRewardDto;
import com.api.taskfy.modules.reward.repositories.RewardRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateRewardService {
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(String userId, String rewardId, UpdateRewardDto updateRewardDto) {
        var rewardFound = this.rewardRepository.findById(rewardId);

        if (rewardFound.isEmpty()) {
            throw new RewardNotFoundException();
        }

        var reward = rewardFound.get();

        var rankingFound = this.rankingRepository.findById(reward.getRankingId());

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        var ranking = rankingFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, ranking.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER &&
                taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        if (updateRewardDto.title != null && !updateRewardDto.title.isBlank()) {
            reward.setTitle(updateRewardDto.title);
        }

        if (updateRewardDto.description != null && !updateRewardDto.description.isBlank()) {
            reward.setDescription(updateRewardDto.description);
        }

        this.rewardRepository.save(reward);
    }
}
