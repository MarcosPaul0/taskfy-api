package com.api.taskfy.modules.taskGroup.useCases.findMyTaskGroups;

import com.api.taskfy.modules.taskGroup.dtos.TaskGroupWithUsersDto;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindMyTaskGroupsService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    public List<TaskGroupWithUsersDto> execute(String userId) {
        var myTaskGroups = this.taskGroupRepository.findByOwnerId(userId);

        return myTaskGroups.stream().map(taskGroup -> {
            Pageable pageable = PageRequest.of(0, 3);
            var groupParticipants = this.taskGroupUserRepository
                    .findByGroupIdWithParticipation(taskGroup.getId(), pageable);

            var users = groupParticipants.stream().map(participant -> {
                return participant.getUser();
            }).collect(Collectors.toList());

            var taskGroupWithUsers = new TaskGroupWithUsersDto();

            taskGroupWithUsers.setTaskGroup(taskGroup);
            taskGroupWithUsers.setUsers(users);

            return taskGroupWithUsers;
        }).collect(Collectors.toList());
    }
}
