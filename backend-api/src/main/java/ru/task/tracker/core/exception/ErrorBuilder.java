package ru.task.tracker.core.exception;

public record ErrorBuilder(String message, int status, String timestamp) {}
