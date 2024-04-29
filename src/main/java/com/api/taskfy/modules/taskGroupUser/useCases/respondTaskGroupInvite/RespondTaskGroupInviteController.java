package com.api.taskfy.modules.taskGroupUser.useCases.respondTaskGroupInvite;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.RespondTaskGroupInviteDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class RespondTaskGroupInviteController {
    @Autowired
    RespondTaskGroupInviteService respondTaskGroupInviteService;

    @PatchMapping("/respond/invite/{groupUserId}")
    public ResponseEntity<Void> handle(
            @PathVariable("groupUserId") String groupUserId,
            @Valid @RequestBody RespondTaskGroupInviteDto respondTaskGroupInviteDto,
            @AuthenticationPrincipal User user
    ) {
        this.respondTaskGroupInviteService.execute(groupUserId, user.getId(), respondTaskGroupInviteDto);

        return ResponseEntity.ok().build();
    }
}
