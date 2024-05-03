package com.api.taskfy.modules.taskGroup.useCases.createTaskGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.dtos.CreateTaskGroupDto;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class CreateTaskGroupController {
    @Autowired
    CreateTaskGroupService createTaskGroupService;

    @PostMapping
    public ResponseEntity<TaskGroup> handle(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateTaskGroupDto createTaskGroupDto
    ) {
        var ownerId = user.getId();

        var taskGroup = this.createTaskGroupService.execute(ownerId, createTaskGroupDto);

        return ResponseEntity.ok(taskGroup);
    }
}
