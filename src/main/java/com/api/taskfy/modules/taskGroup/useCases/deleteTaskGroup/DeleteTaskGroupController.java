package com.api.taskfy.modules.taskGroup.useCases.deleteTaskGroup;

import com.api.taskfy.constants.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class DeleteTaskGroupController {
    @Autowired
    DeleteTaskGroupService deleteTaskGroupService;

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> handle(@PathVariable("groupId") String groupId) {
        this.deleteTaskGroupService.execute(groupId);

        return ResponseEntity.ok().build();
    }
}
