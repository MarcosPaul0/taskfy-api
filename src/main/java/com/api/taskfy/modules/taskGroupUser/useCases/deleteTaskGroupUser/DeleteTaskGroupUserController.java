package com.api.taskfy.modules.taskGroupUser.useCases.deleteTaskGroupUser;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class DeleteTaskGroupUserController {
    @Autowired
    DeleteTaskGroupUserService deleteTaskGroupUserService;

    @DeleteMapping("/{taskGroupId}/{userId}")
    public ResponseEntity<Void> handle(
            @PathVariable("taskGroupId") String taskGroupId,
            @PathVariable("userId") String userId,
            @AuthenticationPrincipal User user
    ) {
        this.deleteTaskGroupUserService.execute(user, taskGroupId, userId);

        return ResponseEntity.ok().build();
    }
}
