package com.api.taskfy.modules.taskGroupUser.useCases.inviteTaskGroupUsers;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.InviteTaskGroupUsersDto;
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
public class InviteTaskGroupUsersController {
    @Autowired
    InviteTaskGroupUsersService inviteTaskGroupUsersService;

    @PostMapping("/send/invite-users")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody InviteTaskGroupUsersDto inviteTaskGroupUsersDto
    ) {
        System.out.println("TESTE INICIO");
        this.inviteTaskGroupUsersService.execute(user.getId(), inviteTaskGroupUsersDto);

        return ResponseEntity.ok().build();
    }
}
