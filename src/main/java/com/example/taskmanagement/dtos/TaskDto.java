package com.example.taskmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private long id;

    private String taskName;
    private String description;
    private String status;
    private LocalDate dateAdded;
    private LocalDate estimatedFinishDate;
}