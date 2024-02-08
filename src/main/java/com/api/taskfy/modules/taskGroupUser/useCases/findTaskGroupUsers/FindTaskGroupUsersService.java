package com.api.taskfy.modules.taskGroupUser.useCases.findTaskGroupUsers;

import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskGroupUsersService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public List<TaskGroupUser> execute(String groupId) {
        return this.taskGroupUserRepository.findByGroupIdAndStatus(groupId, InviteStatus.ACCEPTED, RequestStatus.ACCEPTED);
    }

}
