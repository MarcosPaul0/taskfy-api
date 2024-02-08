package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUserByUserId;

import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskGroupUserByUserIdService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public List<TaskGroupUser> execute(User user) {
        return this.taskGroupUserRepository.findAllByUserIdAndInviteStatus(user.getId(), InviteStatus.PENDING);
    }
}
