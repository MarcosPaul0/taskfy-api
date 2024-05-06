package com.api.taskfy.modules.taskGroupUser.useCases.deleteTaskGroupUser;

import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotBelongsException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskGroupUserService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(User user, String taskGroupId, String userId) {
        var taskGroupUseToDeleteFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, taskGroupId);

        if (taskGroupUseToDeleteFound.isEmpty()) {
            throw new TaskGroupUserNotFoundException();
        }

        var taskGroupUserToDelete = taskGroupUseToDeleteFound.get();

        var taskGroupUserFound =
                this.taskGroupUserRepository.findByUserIdAndGroupId(user.getId(), taskGroupUserToDelete.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupUserNotBelongsException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER &&
                taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        this.taskGroupUserRepository.deleteById(taskGroupUserToDelete.getId());
    }
}
