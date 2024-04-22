package com.api.taskfy.modules.user.useCases.findUsersByGroup;

import com.api.taskfy.errors.task.UserNotBelongsToTaskGroupException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUsersByGroupService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    UserRepository userRepository;

    public List<User> execute(String groupId, String userId, String email, String username) {
        var taskGroupFound = this.taskGroupRepository.findById(groupId);

        if (taskGroupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new UserNotBelongsToTaskGroupException();
        }



        return this.userRepository.findByGroup(groupId, email, username);
    }

}
