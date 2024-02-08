package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUserByGroupId;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.TASK_GROUP_USER)
public class FindTaskGroupUserByGroupIdController {
    @Autowired
    FindTaskGroupUserByGroupIdService findTaskGroupUserByGroupIdService;

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<TaskGroupUser>> handle(HttpServletRequest request) {
        TaskGroup taskGroup = (TaskGroup) request.getAttribute("taskGroup");

        var taskGroupUserList = this.findTaskGroupUserByGroupIdService.execute(taskGroup.getId());

        return ResponseEntity.ok(taskGroupUserList);
    }
}
