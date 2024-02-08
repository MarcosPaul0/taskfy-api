package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUsers;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class FindTaskGroupUsersController {
    @Autowired
    FindTaskGroupUsersService findTaskGroupUsersService;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<TaskGroupUser>> handle(@PathVariable("groupId") String groupId) {
        var taskGroupUserList = this.findTaskGroupUsersService.execute(groupId);

        return ResponseEntity.ok(taskGroupUserList);
    }
}
