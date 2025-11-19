package ru.task.tracker.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    private String title;
    private String description;

}
