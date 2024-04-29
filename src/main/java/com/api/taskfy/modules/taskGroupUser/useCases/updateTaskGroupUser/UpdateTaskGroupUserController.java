package com.api.taskfy.modules.taskGroupUser.useCases.updateTaskGroupUser;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.dtos.UpdateTaskGroupUserDto;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class UpdateTaskGroupUserController {
    @Autowired
    UpdateTaskGroupUserService updateTaskGroupUserService;

    @PatchMapping("/{groupUserId}")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("groupUserId") String groupUserId,
            @Valid @RequestBody UpdateTaskGroupUserDto updateTaskGroupUserDto
    ) {
        this.updateTaskGroupUserService.execute(user.getId(), groupUserId, updateTaskGroupUserDto);

        return ResponseEntity.ok().build();
    }
}
