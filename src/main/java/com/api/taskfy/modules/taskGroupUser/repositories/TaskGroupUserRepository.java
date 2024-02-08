package com.api.taskfy.modules.taskGroupUser.repositories;

import com.api.taskfy.modules.taskGroupUser.entities.TaskGroupUser;
import com.api.taskfy.modules.taskGroupUser.enums.InviteStatus;
import com.api.taskfy.modules.taskGroupUser.enums.RequestStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskGroupUserRepository extends JpaRepository<TaskGroupUser, String> {
    Optional<TaskGroupUser> findByUserIdAndGroupId(String userId, String groupId);
    List<TaskGroupUser> findAllByGroupIdAndRequestStatus(String groupId, RequestStatus requestStatus);
    List<TaskGroupUser> findAllByUserIdAndInviteStatus(String userId, InviteStatus inviteStatus);
    @Transactional
    @Modifying
    @Query("FROM task_group_user tgu WHERE tgu.groupId = :groupId AND (tgu.inviteStatus = :inviteStatus OR tgu.requestStatus = :requestStatus)")
    List<TaskGroupUser> findByGroupIdAndStatus(
            @Param("groupId") String groupId,
            @Param("inviteStatus") InviteStatus inviteStatus,
            @Param("requestStatus") RequestStatus requestStatus
    );
}
