package com.api.taskfy.modules.reward.useCases.findRewardById;

import com.api.taskfy.errors.ranking.RankingNotFoundException;
import com.api.taskfy.errors.reward.RewardNotFoundException;
import com.api.taskfy.errors.taskGroup.PrivateGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.reward.entities.Reward;
import com.api.taskfy.modules.reward.repositories.RewardRepository;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindRewardByIdService {
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;


    public Reward execute(String rewardId) {
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

        var taskGroupFound = this.taskGroupRepository.findById(ranking.getGroupId());

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();

        if (taskGroup.getIsPrivate()) {
            throw new PrivateGroupException();
        }

        return reward;
    }
}
