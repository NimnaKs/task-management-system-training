package lk.rumex.taskmanagerapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Schema(description = "Title of the task", example = "Complete project report", required = true)
    private String title;

    @Schema(description = "Detailed description of the task", example = "The report should cover all aspects of the project.", required = true)
    private String description;

    @Schema(description = "Priority level of the task", example = "HIGH", required = true)
    private Priority priority;

    @Schema(description = "Current status of the task", example = "IN_PROGRESS", required = true)
    private Status status;

    @Schema(description = "Due date for the task", example = "2024-09-30")
    private LocalDate dueDate;

    @Schema(description = "ID of the user assigned to the task", example = "5")
    private Long assignedUserId;
}
