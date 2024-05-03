package com.api.taskfy.modules.taskGroup.policy;


import com.api.taskfy.errors.taskGroup.TaskGroupNotBelongsToUserException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

public class TaskGroupPolicy implements HandlerInterceptor {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();

            var groupId = request.getRequestURI().split("/")[2];

            var groupFound = this.taskGroupRepository.findById(groupId);

            if (groupFound.isEmpty()) {
                throw new TaskGroupNotFoundException();
            }

            var taskGroup = groupFound.get();

            if (taskGroup.getIsPrivate()) {
                var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(user.getId(), taskGroup.getId());

                if (taskGroupUserFound.isEmpty()) {
                    throw new TaskGroupNotBelongsToUserException();
                }

                var taskGroupUser = taskGroupUserFound.get();

                if (taskGroupUser.getInviteStatus() != InviteStatus.ACCEPTED && taskGroupUser.getRequestStatus() != RequestStatus.ACCEPTED) {
                    throw new TaskGroupNotBelongsToUserException();
                }
            }

            request.setAttribute("taskGroup", taskGroup);
        }

        return true;
    }
}
