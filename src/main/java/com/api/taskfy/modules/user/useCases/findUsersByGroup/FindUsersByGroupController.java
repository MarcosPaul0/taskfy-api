package com.api.taskfy.modules.user.useCases.findUsersByGroup;

import com.api.taskfy.constants.Routes;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.USER)
public class FindUsersByGroupController {
    @Autowired
    FindUsersByGroupService findUsersByGroupService;

    @GetMapping("/by-group/{groupId}")
    public ResponseEntity<List<User>> handle(
            @PathVariable("groupId") String groupId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @AuthenticationPrincipal User user
    ) {
        var usersList = this.findUsersByGroupService.execute(
                groupId,
                user.getId(),
                email,
                username
        );

        return ResponseEntity.ok(usersList);
    }
}
