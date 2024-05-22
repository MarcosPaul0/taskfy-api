package com.api.taskfy.modules.reward.useCases.findRewardsByRanking;

import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.taskGroup.PrivateGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.reward.entities.Reward;
import com.api.taskfy.modules.reward.repositories.RewardRepository;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRewardsByRankingService {
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    public List<Reward> execute(String rankingId) {
        var rewardList = this.rewardRepository.findAllByRankingIdOrderByPositionAsc(rankingId);

        var rankingFound = this.rankingRepository.findById(rankingId);

        if (rankingFound.isEmpty()) {
            throw new RankingNotFoundException();
        }

        var ranking = rankingFound.get();

        var taskGroupFound = this.taskGroupRepository.findById(ranking.getGroupId());

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();

        if (taskGroup.getIsPrivate()) {
            throw new PrivateGroupException();
        }

        return rewardList;
    }
}
