package com.api.taskfy.modules.taskGroup.useCases.findTaskGroupById;

import com.api.taskfy.errors.taskGroup.PrivateGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaskGroupByIdService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public TaskGroup execute(String userId, String groupId) {
        var groupFound = this.taskGroupRepository.findById(groupId);

        if (groupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var group = groupFound.get();

        if (group.getIsPrivate()) {
            var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, group.getId());

            if (taskGroupUserFound.isEmpty()) {
                throw new PrivateGroupException();
            }
        }

        return group;
    }
}
