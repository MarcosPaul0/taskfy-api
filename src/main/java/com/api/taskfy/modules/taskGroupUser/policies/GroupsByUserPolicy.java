package com.api.taskfy.modules.taskGroupUser.policies;

import com.api.taskfy.errors.taskGroup.TaskGroupNotBelongsToUserException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class GroupsByUserPolicy  implements HandlerInterceptor {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();

            var groupId = request.getRequestURI().split("/")[3];

            var groupFound = this.taskGroupRepository.findById(groupId);

            if (groupFound.isEmpty()) {
                throw new TaskGroupNotFoundException();
            }

            var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(user.getId(), groupId);

            if (taskGroupUserFound.isEmpty()) {
                throw new TaskGroupNotBelongsToUserException();
            }

            var taskGroupUser = taskGroupUserFound.get();

            if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER &&
                    taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
                throw new UserNotHavePermissionException();
            }

            request.setAttribute("taskGroup", groupFound.get());
        }

        return true;
    }
}
