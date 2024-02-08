package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUserByUserId;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
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
@RequestMapping(Routes.TASK_GROUP_USER)
public class FindTaskGroupUserByUserIdController {
    @Autowired
    FindTaskGroupUserByUserIdService findTaskGroupUserByUserIdService;

    @GetMapping("/user")
    public ResponseEntity<List<TaskGroupUser>> handle(@AuthenticationPrincipal User user) {
        var taskGroupUserList = this.findTaskGroupUserByUserIdService.execute(user);

        return ResponseEntity.ok(taskGroupUserList);
    }
}
