package com.api.taskfy.modules.reward.useCases.findRewardById;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.reward.entities.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.REWARD)
public class FindRewardByIdController {
    @Autowired
    FindRewardByIdService findRewardByIdService;

    @GetMapping("/{rewardId}")
    public ResponseEntity<Reward> handle(@PathVariable("rewardId") String rewardId) {
        var reward = this.findRewardByIdService.execute(rewardId);

        return ResponseEntity.ok(reward);
    }
}
