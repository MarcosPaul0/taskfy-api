package com.api.taskfy.modules.taskGroup.useCases.findTaskGroups;

import com.api.taskfy.modules.taskGroup.entities.TaskGroup;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskGroupsService {
    @Autowired
    TaskGroupRepository taskGroupRepository;

    public List<TaskGroup> execute(String groupName) {
        if (groupName.isEmpty()) {
            return this.taskGroupRepository.findAllByIsPrivateFalseOrderByCreatedAtAsc();
        }

        return this.taskGroupRepository.findAllByNameContainingIgnoreCaseAndIsPrivateFalseOrderByCreatedAtAsc(groupName);
    }
}
