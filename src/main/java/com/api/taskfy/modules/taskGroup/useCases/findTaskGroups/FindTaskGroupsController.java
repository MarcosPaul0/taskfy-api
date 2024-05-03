package com.api.taskfy.modules.taskGroup.useCases.findTaskGroups;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class FindTaskGroupsController {
    @Autowired
    FindTaskGroupsService findTaskGroupsService;

    @GetMapping
    public ResponseEntity<List<TaskGroup>> handle(@RequestParam(name = "groupName", required = false, defaultValue = "") String groupName) {
        var taskGroups = this.findTaskGroupsService.execute(groupName);

        return ResponseEntity.ok(taskGroups);
    }
}
