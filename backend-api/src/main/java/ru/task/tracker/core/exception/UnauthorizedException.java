package ru.task.tracker.core.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
      super("User not authorized");
    }
}
