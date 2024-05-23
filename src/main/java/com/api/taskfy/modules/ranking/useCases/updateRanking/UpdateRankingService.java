package com.api.taskfy.modules.ranking.useCases.updateRanking;

import com.api.taskfy.errors.ranking.AlreadyHasNewRankingException;
import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.dtos.UpdateRankingDto;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateRankingService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    RankingRepository rankingRepository;

    public void execute(String userId, String rankingId, UpdateRankingDto updateRankingDto) {
        var rankingFound = this.rankingRepository.findById(rankingId);

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        var ranking = rankingFound.get();
        var groupId = ranking.getGroupId();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER && taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        var activeRanking = this.rankingRepository.findByPeriodAndGroupId(LocalDateTime.now(), groupId);

        if (!activeRanking.isEmpty() && !activeRanking.getFirst().getId().equals(rankingId)) {
            throw new AlreadyHasNewRankingException();
        }

        ranking.setDueDate(updateRankingDto.dueDate);

        this.rankingRepository.save(ranking);
    }
}
