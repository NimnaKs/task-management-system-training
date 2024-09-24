package lk.rumex.taskmanagerapp.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateDTO {
    @Schema(description = "Title of the task", example = "Complete project report", required = true)
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @Schema(description = "Description of the task", example = "Prepare the final draft of the project report", required = true)
    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Schema(description = "Priority of the task", example = "HIGH", required = true)
    @NotNull(message = "Priority must be specified")
    private Priority priority;

    @Schema(description = "Status of the task", example = "TO_DO", required = true)
    @NotNull(message = "Status must be specified")
    private Status status;

    @Schema(description = "Due date of the task", example = "2023-12-31", required = true)
    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDate dueDate;

    @Schema(description = "ID of the user assigned to this task", example = "1", required = true)
    @NotNull(message = "Assigned user ID cannot be null")
    private Long assignedUserId;
}
