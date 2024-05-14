package com.api.taskfy.modules.taskGroup.useCases.findTaskGroupsWithPaticipation;

import com.api.taskfy.modules.taskGroup.dtos.TaskGroupWithUsersDto;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import com.api.taskfy.modules.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindTaskGroupsWithParticipationService {
    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    public List<TaskGroupWithUsersDto> execute(User user) {
        var usersByTaskGroup = this.taskGroupUserRepository.findByUserWithParticipation(user);

        return usersByTaskGroup.stream().map(taskGroupUser -> {
            var taskGroup = this.taskGroupRepository.findById(taskGroupUser.getGroupId());

            Pageable pageable = PageRequest.of(0, 3);
            var groupParticipants = this.taskGroupUserRepository
                    .findByGroupIdWithParticipation(taskGroupUser.getGroupId(), pageable);

            var users = groupParticipants.stream().map(participant -> {
                return participant.getUser();
            }).collect(Collectors.toList());

            var taskGroupWithUsers = new TaskGroupWithUsersDto();

            taskGroupWithUsers.setTaskGroup(taskGroup.get());
            taskGroupWithUsers.setUsers(users);

            return taskGroupWithUsers;
        }).collect(Collectors.toList());
    }
}
