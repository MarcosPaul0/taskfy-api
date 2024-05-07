package com.api.taskfy.modules.ranking.useCases.findAllRankingsByGroup;

import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.modules.ranking.entities.Ranking;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRankingsByGroupService {
    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public List<Ranking> execute(String userId, String groupId) {
        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        return this.rankingRepository.findAllByGroupId(groupId);
    }

}
