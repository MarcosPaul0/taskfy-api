package com.api.taskfy.modules.task.useCases.findTaskById;

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

@RestController
@RequestMapping(Routes.TASK)
public class FindTaskByIdController {
    @Autowired
    FindTaskByIdService findTaskByIdService;

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("taskId") String taskId
    ) {
        var taskFound = this.findTaskByIdService.execute(user.getId(), taskId);

        return ResponseEntity.ok(taskFound);
    }
}
