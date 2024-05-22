package com.api.taskfy.modules.task.useCases.createTask;

import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.task.dtos.CreateTaskDto;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskGroupUserRepository taskGroupUserRepository;

    public Task execute(String userId, CreateTaskDto createTaskDto) {
        var groupId = createTaskDto.groupId;

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }

        var taskOwner = this.userRepository.findById(createTaskDto.userId);

        if (taskOwner.isEmpty()) {
            throw new UserNotFoundException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        var newTask = new Task(createTaskDto);
        newTask.setUser(taskOwner.get());

        if (taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.NORMAL)) {
            newTask.setIsActive(false);
        }

        if (taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.MANAGER) ||
                taskGroupUser.getTaskGroupRole().equals(TaskGroupRole.OWNER)
        ) {
            newTask.setIsActive(true);
        }

        return this.taskRepository.save(newTask);
    }

}
