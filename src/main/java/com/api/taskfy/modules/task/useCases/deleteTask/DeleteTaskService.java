package com.api.taskfy.modules.task.useCases.deleteTask;

import com.api.taskfy.errors.task.TaskNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(String userId, String taskId) {
        var taskFound = this.taskRepository.findById(taskId);

        if (taskFound.isEmpty()) {
            throw new TaskNotFoundException();
        }

        var task = taskFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, task.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (
                !taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.OWNER) &&
                        !taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.MANAGER)
        ) {
            throw new UserNotHavePermissionException();
        }

        this.taskRepository.deleteTaskById(taskId);
    }
}
