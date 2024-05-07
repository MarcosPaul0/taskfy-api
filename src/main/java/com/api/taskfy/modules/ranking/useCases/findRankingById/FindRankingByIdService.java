package com.api.taskfy.modules.ranking.useCases.findRankingById;

import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.entities.Ranking;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindRankingByIdService {
    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public Ranking execute(String userId, String rankingId) {
        var rankingFound = this.rankingRepository.findById(rankingId);

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        var ranking = rankingFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, ranking.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotHavePermissionException();
        }

        return ranking;
    }

}
