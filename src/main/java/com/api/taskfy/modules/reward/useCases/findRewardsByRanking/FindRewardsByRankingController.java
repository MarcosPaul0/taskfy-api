package com.api.taskfy.modules.reward.useCases.findRewardsByRanking;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.reward.entities.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.REWARD)
public class FindRewardsByRankingController {
    @Autowired
    FindRewardsByRankingService findRewardsByRankingService;

    @GetMapping("/ranking/{rankingId}")
    public ResponseEntity<List<Reward>> handle(@PathVariable("rankingId") String rankingId) {
        var rewardList = this.findRewardsByRankingService.execute(rankingId);

        return ResponseEntity.ok(rewardList);
    }

}
