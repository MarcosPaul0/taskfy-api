package com.api.taskfy.modules.taskGroupUser.useCases.inviteTaskGroupUsers;

import com.api.taskfy.errors.taskGroup.TaskGroupNotBelongsToUserException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.dtos.InviteTaskGroupUsersDto;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class InviteTaskGroupUsersService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    UserRepository userRepository;

    public void execute(String userId, InviteTaskGroupUsersDto inviteTaskGroupUsersDto) {
        var taskGroupFound = this.taskGroupRepository.findById(inviteTaskGroupUsersDto.groupId);

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();
        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, taskGroup.getId());

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupNotBelongsToUserException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER && taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER) {
            throw new UserNotHavePermissionException();
        }

        for (var userEmail : inviteTaskGroupUsersDto.emailList) {
            if (Objects.equals(userEmail, taskGroup.getOwner().getEmail())) {
                continue;
            }

            var userFound = this.userRepository.findByEmail(userEmail);

            if (userFound.isEmpty()) {
                // TODO send invite email
                continue;
            }

            var userToInvite = userFound.get();

            var taskGroupUserAlreadyInvitedFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userToInvite.getId(), taskGroup.getId());

            if (taskGroupUserAlreadyInvitedFound.isEmpty()) {
                var newTaskGroupUser = new TaskGroupUser();

                newTaskGroupUser.setGroupId(taskGroup.getId());
                newTaskGroupUser.setTaskGroupRole(TaskGroupRole.NORMAL);
                newTaskGroupUser.setUser(userToInvite);
                newTaskGroupUser.setInviteStatus(InviteStatus.PENDING);

                this.taskGroupUserRepository.save(newTaskGroupUser);
                continue;
            }

            var taskGroupUserAlreadyInvited = taskGroupUserAlreadyInvitedFound.get();

            taskGroupUserAlreadyInvited.setGroupId(taskGroup.getId());
            taskGroupUserAlreadyInvited.setRequestStatus(null);
            taskGroupUserAlreadyInvited.setInviteStatus(InviteStatus.PENDING);

            this.taskGroupUserRepository.save(taskGroupUserAlreadyInvited);
        }
    }
}
