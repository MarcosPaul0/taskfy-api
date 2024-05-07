package com.api.taskfy.modules.ranking.useCases.findAllRankingsByGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.ranking.entities.Ranking;
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
public class FindAllRankingsByGroupController {
    @Autowired
    FindAllRankingsByGroupService findAllRankingsByGroupService;

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Ranking>> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("groupId") String groupId
    ) {
        var rankingList = this.findAllRankingsByGroupService.execute(user.getId(), groupId);

        return ResponseEntity.ok(rankingList);
    }
}
