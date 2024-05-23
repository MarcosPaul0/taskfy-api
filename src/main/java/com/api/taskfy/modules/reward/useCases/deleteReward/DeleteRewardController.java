package com.api.taskfy.modules.reward.useCases.deleteReward;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.REWARD)
public class DeleteRewardController {
    @Autowired
    DeleteRewardService deleteRewardService;

    @DeleteMapping("/{rewardId}")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("rewardId") String rewardId) {
        this.deleteRewardService.execute(user.getId(), rewardId);

        return ResponseEntity.ok().build();
    }

}
