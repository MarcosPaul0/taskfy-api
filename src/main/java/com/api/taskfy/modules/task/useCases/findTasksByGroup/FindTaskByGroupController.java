package com.api.taskfy.modules.task.useCases.findTasksByGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.TASK)
public class FindTaskByGroupController {
    @Autowired
    FindTaskByGroupService findTaskByGroupService;

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Page<Task>> handle(
            @AuthenticationPrincipal User user,
            @PathVariable("groupId") String groupId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "title", defaultValue = "") String title
    ) {
        var taskList = this.findTaskByGroupService.execute(user.getId(), groupId, title, page);

        return ResponseEntity.ok(taskList);
    }
}
