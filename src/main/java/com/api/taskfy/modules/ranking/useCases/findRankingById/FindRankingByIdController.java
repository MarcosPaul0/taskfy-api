package com.api.taskfy.modules.ranking.useCases.findRankingById;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.ranking.entities.Ranking;
import com.api.taskfy.modules.ranking.entities.UserPlacement;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.RANKING)
public class FindRankingByIdController {
    @Autowired
    FindRankingByIdService findRankingByIdService;

    @GetMapping("/{rankingId}")
    public ResponseEntity<Ranking> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("rankingId") String rankingId
    ) {
        var usersPlacement = this.findRankingByIdService.execute(user.getId(), rankingId);


        return ResponseEntity.ok(usersPlacement);
    }
}
