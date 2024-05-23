package com.api.taskfy.modules.ranking.useCases.updateRanking;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.ranking.dtos.UpdateRankingDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.RANKING)
public class UpdateRankingController {
    @Autowired
    UpdateRankingService updateRankingService;

    @PatchMapping("/{rankingId}")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("rankingId") String rankingId,
            @Valid @RequestBody UpdateRankingDto updateRankingDto
    ) {
        this.updateRankingService.execute(user.getId(), rankingId, updateRankingDto);

        return ResponseEntity.ok().build();
    }
}
