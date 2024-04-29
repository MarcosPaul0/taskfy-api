package com.api.taskfy.modules.taskGroupUser.useCases.inviteTaskGroupUser;

import com.api.taskfy.errors.taskGroup.TaskGroupNotBelongsToUserException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserInvitationAlreadyExistsException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.dtos.CreateTaskGroupUserDto;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteTaskGroupUserService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    UserRepository userRepository;

    public void execute(String userId, CreateTaskGroupUserDto createTaskGroupUserDto) {
        var taskGroupFound = this.taskGroupRepository.findById(createTaskGroupUserDto.groupId);

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();

        if (!userId.equals(taskGroup.getOwner().getId())) {
            throw new TaskGroupNotBelongsToUserException();
        }

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(createTaskGroupUserDto.userId, createTaskGroupUserDto.groupId);

        if (taskGroupUserFound.isEmpty()) {
            var userFound = this.userRepository.findById(createTaskGroupUserDto.userId);

            if (userFound.isEmpty()) {
                throw new UserNotFoundException();
            }

            var user = userFound.get();
            var newTaskGroupUser = new TaskGroupUser(createTaskGroupUserDto);

            newTaskGroupUser.setUser(user);
            newTaskGroupUser.setInviteStatus(InviteStatus.PENDING);

            this.taskGroupUserRepository.save(newTaskGroupUser);

            return;
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getInviteStatus() != null) {
            throw new TaskGroupUserInvitationAlreadyExistsException();
        }

        taskGroupUser.setRequestStatus(null);

        taskGroupUser.setInviteStatus(InviteStatus.PENDING);

        this.taskGroupUserRepository.save(taskGroupUser);
    }
}
