package com.api.taskfy.modules.taskGroupUser.useCases.inviteTaskGroupUser;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.CreateTaskGroupUserDto;
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

    @PostMapping("/send/invite")
    public ResponseEntity<Void> handle(
            @Valid @RequestBody CreateTaskGroupUserDto createTaskGroupUserDto,
            @AuthenticationPrincipal User user
    ) {
        this.inviteTaskGroupUserService.execute(user.getId(), createTaskGroupUserDto);

        return ResponseEntity.ok().build();
    }
}
