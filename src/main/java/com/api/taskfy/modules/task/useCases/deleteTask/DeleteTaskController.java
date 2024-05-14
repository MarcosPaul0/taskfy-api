package com.api.taskfy.modules.task.useCases.deleteTask;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.TASK)
public class DeleteTaskController {
    @Autowired
    DeleteTaskService deleteTaskService;

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("taskId") String taskId
    ) {
        this.deleteTaskService.execute(user.getId(), taskId);

        return ResponseEntity.ok().build();
    }


}
