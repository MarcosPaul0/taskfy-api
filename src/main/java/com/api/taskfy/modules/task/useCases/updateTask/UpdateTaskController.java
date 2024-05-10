package com.api.taskfy.modules.task.useCases.updateTask;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.task.dtos.UpdateTaskDto;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK)
public class UpdateTaskController {
    @Autowired
    UpdateTaskService updateTaskService;

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("taskId") String taskId,
            @Valid @RequestBody UpdateTaskDto updateTaskDto
    ) {
        var updatedTask = this.updateTaskService.execute(user.getId(), taskId, updateTaskDto);

        return ResponseEntity.ok(updatedTask);
    }
}
