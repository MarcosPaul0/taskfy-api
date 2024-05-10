package com.api.taskfy.modules.taskGroupUser.useCases.requestTaskGroupEntry;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class RequestTaskGroupUserController {
    @Autowired
    RequestTaskGroupUserService requestTaskGroupUserService;

    @PostMapping("/send/request/{groupId}")
    public ResponseEntity<Void> handle(@PathVariable("groupId") String groupId, @AuthenticationPrincipal User user) {
        this.requestTaskGroupUserService.execute(groupId, user.getId());

        return ResponseEntity.ok().build();
    }
}
