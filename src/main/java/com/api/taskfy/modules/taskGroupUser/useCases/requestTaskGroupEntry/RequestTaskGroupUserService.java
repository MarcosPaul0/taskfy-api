package com.api.taskfy.modules.taskGroupUser.useCases.requestTaskGroupEntry;

import com.api.taskfy.errors.taskGroupUser.TaskGroupUserRequestAlreadyExistsException;
import com.api.taskfy.errors.user.UserNotFoundException;
import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestTaskGroupUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(String groupId, String userId) {
        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            var userFound = this.userRepository.findById(userId);

            if (userFound.isEmpty()) {
                throw new UserNotFoundException();
            }

            var user = userFound.get();
            var newTaskGroupUser = new TaskGroupUser();

            newTaskGroupUser.setGroupId(groupId);
            newTaskGroupUser.setUser(user);
            newTaskGroupUser.setTaskGroupRole(TaskGroupRole.NORMAL);
            newTaskGroupUser.setRequestStatus(RequestStatus.PENDING);

            this.taskGroupUserRepository.save(newTaskGroupUser);

            return;
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getRequestStatus() != null) {
            throw new TaskGroupUserRequestAlreadyExistsException();
        }

        taskGroupUser.setInviteStatus(null);
        taskGroupUser.setRequestStatus(RequestStatus.PENDING);

        this.taskGroupUserRepository.save(taskGroupUser);
    }
}
