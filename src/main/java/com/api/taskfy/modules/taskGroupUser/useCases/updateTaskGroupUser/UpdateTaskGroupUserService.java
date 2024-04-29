package com.api.taskfy.modules.taskGroupUser.useCases.updateTaskGroupUser;

import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotFoundException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserOwnerNotFoundException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.dtos.UpdateTaskGroupUserDto;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskGroupUserService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    UserRepository userRepository;

    public void execute(String userId, String groupUserId, UpdateTaskGroupUserDto updateTaskGroupUserDto) {
        var userFound = this.userRepository.findById(userId);

        if (userFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = userFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findById(groupUserId);

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupUserNotFoundException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        var newRole = TaskGroupRole.valueOf(updateTaskGroupUserDto.taskGroupRole);

        if (newRole == TaskGroupRole.OWNER) {
            var taskGroupFound = taskGroupRepository.findById(taskGroupUser.getGroupId());

            if (taskGroupFound.isEmpty()) {
                throw new TaskGroupNotFoundException();
            }

            var group = taskGroupFound.get();
            var groupOwner = group.getOwner();

            var currentTaskGroupUserOwnerFound = this.taskGroupUserRepository.findByUserIdAndGroupId(groupOwner.getId(), group.getId());

            if (currentTaskGroupUserOwnerFound.isEmpty()) {
                throw new TaskGroupUserOwnerNotFoundException();
            }

            var currentTaskGroupUserOwner = currentTaskGroupUserOwnerFound.get();

            currentTaskGroupUserOwner.setTaskGroupRole(TaskGroupRole.MANAGER);
            group.setOwner(user);

            this.taskGroupRepository.save(group);
            this.taskGroupUserRepository.save(currentTaskGroupUserOwner);
        }

        taskGroupUser.setTaskGroupRole(TaskGroupRole.valueOf(updateTaskGroupUserDto.taskGroupRole));
        this.taskGroupUserRepository.save(taskGroupUser);
    }
}
