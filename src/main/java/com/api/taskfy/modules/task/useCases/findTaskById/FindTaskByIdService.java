package com.api.taskfy.modules.task.useCases.findTaskById;

import com.api.taskfy.errors.task.TaskNotFoundException;
import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaskByIdService {
    @Autowired
    TaskRepository taskRepository;


    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public Task execute(String userId, String taskId) {
        var taskFound = this.taskRepository.findById(taskId);

        if (taskFound.isEmpty()) {
            throw new TaskNotFoundException();
        }

        var task = taskFound.get();

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, task.getGroupId());

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        return task;
    }
}
