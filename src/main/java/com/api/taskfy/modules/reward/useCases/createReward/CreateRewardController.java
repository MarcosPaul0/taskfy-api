package com.api.taskfy.modules.reward.useCases.createReward;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.reward.dtos.CreateRewardDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.REWARD)
public class CreateRewardController {
    @Autowired
    CreateRewardService createRewardService;

    @PostMapping()
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateRewardDto createRewardDto
    ) {
        this.createRewardService.execute(user.getId(), createRewardDto);

        return ResponseEntity.ok().build();
    }
}
