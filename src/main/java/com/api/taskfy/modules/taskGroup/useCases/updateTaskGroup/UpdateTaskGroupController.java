package com.api.taskfy.modules.taskGroup.useCases.updateTaskGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.dtos.UpdateTaskGroupDto;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class UpdateTaskGroupController {
    @Autowired
    UpdateTaskGroupService updateTaskGroupService;

    @PatchMapping("{groupId}")
    public ResponseEntity<TaskGroup> handle(HttpServletRequest request, @Valid @RequestBody UpdateTaskGroupDto updateTaskGroupDto) {
        TaskGroup taskGroup = (TaskGroup) request.getAttribute("taskGroup");

        var updatedTaskGroup = this.updateTaskGroupService.execute(taskGroup, updateTaskGroupDto);

        return ResponseEntity.ok(updatedTaskGroup);
    }
}
