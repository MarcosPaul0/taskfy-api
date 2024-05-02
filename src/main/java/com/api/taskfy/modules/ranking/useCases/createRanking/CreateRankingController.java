package com.api.taskfy.modules.ranking.useCases.createRanking;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.ranking.dtos.CreateRankingDto;
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
@RequestMapping(Routes.RANKING)
public class CreateRankingController {
    @Autowired
    CreateRankingService createRankingService;

    @PostMapping
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateRankingDto createRankingDto
    ) {
        this.createRankingService.execute(user.getId(), createRankingDto);

        return ResponseEntity.ok().build();
    }
}
