package com.api.taskfy.modules.taskGroup.useCases.findTaskGroupById;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class FindTaskGroupByIdController {
    @Autowired
    FindTaskGroupByIdService findTaskGroupByIdService;

    @GetMapping("/{groupId}")
    public ResponseEntity<TaskGroup> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("groupId") String groupId
    ) {
        var groupFound = this.findTaskGroupByIdService.execute(user.getId(), groupId);

        return ResponseEntity.ok(groupFound);
    }
}
