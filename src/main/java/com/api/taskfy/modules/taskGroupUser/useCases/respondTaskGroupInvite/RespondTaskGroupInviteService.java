package com.api.taskfy.modules.taskGroupUser.useCases.respondTaskGroupInvite;

import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotBelongsException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotFoundException;
import com.api.taskfy.modules.taskGroupUser.dtos.RespondTaskGroupInviteDto;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespondTaskGroupInviteService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public void execute(String groupUserId, String userId, RespondTaskGroupInviteDto respondTaskGroupInviteDto) {
        var taskGroupUserFound = this.taskGroupUserRepository.findById(groupUserId);

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupUserNotFoundException();
        }

        var updatedTaskGroupUser = taskGroupUserFound.get();

        if (!updatedTaskGroupUser.getUser().getId().equals(userId)) {
            throw new TaskGroupUserNotBelongsException();
        }

        updatedTaskGroupUser.setInviteStatus(InviteStatus.valueOf(respondTaskGroupInviteDto.inviteStatus));

        this.taskGroupUserRepository.save(updatedTaskGroupUser);
    }
}
