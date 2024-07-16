package com.api.taskfy.modules.ranking.useCases.findActiveRankingByGroup;

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

@RestController
@RequestMapping(Routes.RANKING)
public class FindActiveRankingByGroupController {
    @Autowired
    FindActiveRankingByGroupService findActiveRankingByGroupService;

    @GetMapping("/active/{taskGroupId}")
    public ResponseEntity<Ranking> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("taskGroupId") String taskGroupId
    ) {
        var taskGroup = this.findActiveRankingByGroupService.execute(user.getId(), taskGroupId);

        return ResponseEntity.ok(taskGroup);
    }
}
