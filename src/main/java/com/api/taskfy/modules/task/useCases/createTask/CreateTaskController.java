package com.api.taskfy.modules.task.useCases.createTask;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.task.dtos.CreateTaskDto;
import com.api.taskfy.modules.task.entities.Task;
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
@RequestMapping(Routes.TASK)
public class CreateTaskController {
    @Autowired
    CreateTaskService createTaskService;

    @PostMapping
    public ResponseEntity<Task> handle(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateTaskDto createTaskDto
    ) {
        var newTask = this.createTaskService.execute(user.getId(), createTaskDto);

        return ResponseEntity.ok(newTask);
    }
}
