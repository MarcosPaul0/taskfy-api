package com.api.taskfy.modules.ranking.useCases.createRanking;

import com.api.taskfy.errors.ranking.AlreadyHasNewRankingException;
import com.api.taskfy.errors.taskGroup.TaskGroupNotFoundException;
import com.api.taskfy.errors.taskGroupUser.TaskGroupUserNotFoundException;
import com.api.taskfy.errors.user.UserNotHavePermissionException;
import com.api.taskfy.modules.ranking.dtos.CreateRankingDto;
import com.api.taskfy.modules.ranking.entities.Ranking;
import com.api.taskfy.modules.ranking.entities.UserPlacement;
import com.api.taskfy.modules.ranking.repositories.RankingRepository;
import com.api.taskfy.modules.ranking.repositories.UserPlacementRepository;
import com.api.taskfy.modules.taskGroup.repositories.TaskGroupRepository;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import com.api.taskfy.modules.taskGroupUser.enums.TaskGroupRole;
import com.api.taskfy.modules.taskGroupUser.repositories.TaskGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateRankingService {
    @Autowired
    RankingRepository rankingRepository;

    @Autowired
    UserPlacementRepository userPlacementRepository;

    @Autowired
    TaskGroupUserRepository taskGroupUserRepository;

    @Autowired
    TaskGroupRepository taskGroupRepository;

    public void execute(String userId, CreateRankingDto createRankingDto) {
        var groupId = createRankingDto.groupId;

        var groupFound = this.taskGroupRepository.findById(groupId);

        if (groupFound.isEmpty()) {
            throw new TaskGroupNotFoundException();
        }

        var taskGroupUserFound = this.taskGroupUserRepository.findByUserIdAndGroupId(userId, groupId);

        if (taskGroupUserFound.isEmpty()) {
            throw new TaskGroupUserNotFoundException();
        }

        var taskGroupUser = taskGroupUserFound.get();

        if (taskGroupUser.getTaskGroupRole() != TaskGroupRole.OWNER &&
                taskGroupUser.getTaskGroupRole() != TaskGroupRole.MANAGER) {
            throw new UserNotHavePermissionException();
        }

        var rankingFound =
                this.rankingRepository.findByPeriodAndGroupId(LocalDateTime.now(), groupId);

        if (!rankingFound.isEmpty()) {
            throw new AlreadyHasNewRankingException();
        }

        var newRanking = new Ranking(createRankingDto);

        var savedRanking = this.rankingRepository.save(newRanking);

        var taskGroupUsers =
                this.taskGroupUserRepository.findByGroupIdAndStatus(groupId, InviteStatus.ACCEPTED, RequestStatus.ACCEPTED);

        List<UserPlacement> userPlacements = taskGroupUsers.stream()
                .map(taskGroupUserRanking -> {
                    UserPlacement userPlacement = new UserPlacement();

                    userPlacement.setUser(taskGroupUserRanking.getUser());
                    userPlacement.setRanking(savedRanking);
                    userPlacement.setPoints(0);

                    return userPlacement;
                }).toList();

        this.userPlacementRepository.saveAll(userPlacements);
    }
}
