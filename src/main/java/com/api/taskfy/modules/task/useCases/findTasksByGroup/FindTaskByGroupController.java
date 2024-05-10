package com.api.taskfy.modules.task.useCases.findTasksByGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.task.entities.Task;
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
@RequestMapping(Routes.TASK)
public class FindTaskByGroupController {
    @Autowired
    FindTaskByGroupService findTaskByGroupService;

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Task>> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("groupId") String groupId
    ) {
        var taskList = this.findTaskByGroupService.execute(user.getId(), groupId);

        return ResponseEntity.ok(taskList);
    }
}
