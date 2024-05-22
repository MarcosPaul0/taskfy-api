package com.api.taskfy.modules.reward.useCases.updateReward;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.reward.dtos.UpdateRewardDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.REWARD)
public class UpdateRewardController {
    @Autowired
    UpdateRewardService updateRewardService;

    @PatchMapping("/{rewardId}")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("rewardId") String rewardId,
            @Valid @RequestBody UpdateRewardDto updateRewardDto
            ) {
        this.updateRewardService.execute(user.getId(), rewardId, updateRewardDto);

        return ResponseEntity.ok().build();
    }
}
