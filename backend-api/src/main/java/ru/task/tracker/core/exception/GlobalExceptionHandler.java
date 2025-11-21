package ru.task.tracker.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorBuilder> handleTaskNotFound(TaskNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorBuilder> handleUserNotFound(UserNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorBuilder> handleBadRequest(BadRequestException ex) {
        return buildError(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorBuilder> handleUnauthorizedRequest(UnauthorizedException ex) {
        return buildError(HttpStatus.UNAUTHORIZED,  ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorBuilder> handleBadCredentialsRequest(AuthenticationException ex) {
        return buildError(HttpStatus.UNAUTHORIZED,  ex.getMessage());
    }

//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity<ApiError> handleForbidden(ForbiddenException ex) {
//        return buildError(HttpStatus.FORBIDDEN, ex.getMessage());
//    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorBuilder> handleOther(Exception ex) {
//        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error: " + ex.getMessage());
//    }


    private ResponseEntity<ErrorBuilder> buildError(HttpStatus status, String message) {
        ErrorBuilder error = new ErrorBuilder(
                message,
                status.value(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(status).body(error);
    }
}
