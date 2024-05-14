package com.api.taskfy.modules.taskGroup.useCases.findTaskGroupsWithPaticipation;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.taskGroup.dtos.TaskGroupWithUsersDto;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Routes.TASK_GROUP)
public class FindTaskGroupsWithParticipationController {
    @Autowired
    FindTaskGroupsWithParticipationService findTaskGroupsWithParticipationService;

    @GetMapping("/with/participation")
    public ResponseEntity<List<TaskGroupWithUsersDto>> handle(@AuthenticationPrincipal User user) {
        var taskGroups = this.findTaskGroupsWithParticipationService.execute(user);

        return ResponseEntity.ok(taskGroups);
    }
}
