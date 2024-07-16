package com.api.taskfy.modules.ranking.useCases.findActiveRankingByGroup;

import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.modules.ranking.entities.Ranking;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindActiveRankingByGroupService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    RankingRepository rankingRepository;

    public Ranking execute(String userId, String taskGroupId) {
        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, taskGroupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var rankingFound = this.rankingRepository.findCurrentRankingByGroupId(taskGroupId);

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        return rankingFound.get();
    }
}
