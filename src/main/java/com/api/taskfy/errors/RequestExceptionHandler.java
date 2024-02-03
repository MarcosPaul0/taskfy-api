package com.api.taskfy.errors;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.api.taskfy.errors.ranking.*;
import com.api.taskfy.errors.reward.RewardNotFoundException;
import com.api.taskfy.errors.task.*;
import com.api.taskfy.errors.taskGroup.*;
import com.api.taskfy.errors.taskGroupUser.*;
import com.api.taskfy.errors.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RequestExceptionHandler {
    private ResponseEntity<Object> handleDefaultException(DefaultException exception) {
         ApiException apiException = new ApiException(
            exception.getMessage(),
            exception.getStatus(),
            ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, exception.getStatus());
    }

    @ExceptionHandler(value = {DefaultException.class})
    public ResponseEntity<Object> handleRuntimeException(DefaultException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExceptionException(UserAlreadyExistsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
       return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidRefreshTokenException.class})
    public ResponseEntity<Object> handleInvalidRefreshTokenException(InvalidRefreshTokenException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {RefreshTokenExpiredException.class})
    public ResponseEntity<Object> handleRefreshTokenExpiredException(RefreshTokenExpiredException exception) {
        return this.handleDefaultException(exception);
    }


    @ExceptionHandler(value = {TaskGroupAlreadyExistsException.class})
    public ResponseEntity<Object> handleTaskGroupAlreadyExistsException(TaskGroupAlreadyExistsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupNotFoundException.class})
    public ResponseEntity<Object> handleTaskGroupNotFoundException(TaskGroupNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupNotBelongsToUserException.class})
    public ResponseEntity<Object> handleTaskGroupNotBelongsToUserException(TaskGroupNotBelongsToUserException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupUserNotFoundException.class})
    public ResponseEntity<Object> handleTaskGroupUserNotFoundException(TaskGroupUserNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupUserRequestAlreadyExistsException.class})
    public ResponseEntity<Object> handleTaskGroupUserRequestAlreadyExistsException(TaskGroupUserRequestAlreadyExistsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupUserInvitationAlreadyExistsException.class})
    public ResponseEntity<Object> handleTaskGroupUserInvitationAlreadyExistsException(TaskGroupUserInvitationAlreadyExistsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidTaskGroupRoleException.class})
    public ResponseEntity<Object> handleInvalidTaskGroupRoleException(InvalidTaskGroupRoleException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupUserOwnerNotFoundException.class})
    public ResponseEntity<Object> handleTaskGroupUserOwnerNotFoundException(TaskGroupUserOwnerNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidRequestStatusException.class})
    public ResponseEntity<Object> handleInvalidRequestStatusException(InvalidRequestStatusException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidInviteStatusException.class})
    public ResponseEntity<Object> handleInvalidInviteStatusException(InvalidInviteStatusException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidTaskStatusException.class})
    public ResponseEntity<Object> handleInvalidTaskStatusException(InvalidTaskStatusException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskGroupUserNotBelongsException.class})
    public ResponseEntity<Object> handleTaskGroupUserNotBelongsException(TaskGroupUserNotBelongsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {TaskNotFoundException.class})
    public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {UserNotBelongsToTaskGroupException.class})
    public ResponseEntity<Object> handleUserNotBelongsToTaskGroupException(UserNotBelongsToTaskGroupException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {UserNotHavePermissionException.class})
    public ResponseEntity<Object> handleUserNotHavePermissionException(UserNotHavePermissionException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {RankingAlreadyExistsException.class})
    public ResponseEntity<Object> handleRankingAlreadyExistsException(RankingAlreadyExistsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {InvalidCredentialsException.class})
    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {RankingNotFoundException.class})
    public ResponseEntity<Object> handleRankingNotFoundException(RankingNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {AlreadyHasNewRankingException.class})
    public ResponseEntity<Object> handleAlreadyHasNewRankingException(AlreadyHasNewRankingException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {RankingExpiredException.class})
    public ResponseEntity<Object> handleRankingExpiredException(RankingExpiredException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(value = {RewardNotFoundException.class})
    public ResponseEntity<Object> handleRewardNotFoundException(RewardNotFoundException exception) {
        return this.handleDefaultException(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
