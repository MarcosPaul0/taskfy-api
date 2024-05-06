package com.api.taskfy.modules.taskGroupUser.useCases.inviteTaskGroupUser;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.InviteUserDto;
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
@RequestMapping(Routes.TASK_GROUP_USER)
public class InviteTaskGroupUserController {
    @Autowired
    InviteTaskGroupUserService inviteTaskGroupUserService;

    @PostMapping("/send/invite-user")
    public ResponseEntity<Void> handle(
            @Valid @RequestBody InviteUserDto inviteUserDto,
            @AuthenticationPrincipal User user
    ) {
        this.inviteTaskGroupUserService.execute(user.getId(), inviteUserDto);

        return ResponseEntity.ok().build();
    }
}
