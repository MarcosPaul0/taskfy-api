package com.api.taskfy.modules.task.useCases.findTasksByGroup;

import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.task.entities.Task;
import com.api.taskfy.modules.task.repositories.TaskRepository;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskByGroupService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public Page<Task> execute(String userId, String groupId, String title, int page) {
        var taskGroupFound = this.taskGroupRepository.findById(groupId);

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroup = taskGroupFound.get();

        if (taskGroup.getIsPrivate()) {
            var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

            if (taskGroupUserFound.isEmpty()) {
                throw new UserNotBelongsToTaskGroupException();
            }
        }

        Pageable sortedByCreatedAt =
                PageRequest.of(page, 10, Sort.by("createdAt").descending());

        return this.taskRepository.findAllByGroupIdAndTitleContaining(groupId, title, sortedByCreatedAt);
    }
}
