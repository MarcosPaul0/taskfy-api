package com.api.taskfy.modules.taskGroupUser.useCases.respondTaskGroupRequest;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.RespondTaskGroupRequestDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class RespondTaskGroupRequestController {
    @Autowired
    RespondTaskGroupRequestService respondTaskGroupRequestService;

    @PatchMapping("/respond/request/{groupUserId}")
    public ResponseEntity<Void> handle(
            @PathVariable("groupUserId") String groupUserId,
            @Valid @RequestBody RespondTaskGroupRequestDto respondTaskGroupRequestDto,
            @AuthenticationPrincipal User user
    ) {
        this.respondTaskGroupRequestService.execute(user.getId(), groupUserId, respondTaskGroupRequestDto);

        return ResponseEntity.ok().build();
    }
}
