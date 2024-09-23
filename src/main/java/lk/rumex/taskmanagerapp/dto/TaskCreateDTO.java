package lk.rumex.taskmanagerapp.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateDTO {
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Priority must be specified")
    private Priority priority;

    @NotNull(message = "Status must be specified")
    private Status status;

    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDate dueDate;

    @NotNull(message = "Assigned user ID cannot be null")
    private Long assignedUserId;
}