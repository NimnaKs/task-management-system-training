package lk.rumex.taskmanagerapp.repository;

import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lk.rumex.taskmanagerapp.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t WHERE "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:priority IS NULL OR t.priority = :priority) AND "
            + "(:dueDate IS NULL OR t.dueDate = :dueDate)")
    Page<Task> findAllWithFilters(
            @Param("status") Status status,
            @Param("priority") Priority priority,
            @Param("dueDate") LocalDate dueDate,
            Pageable pageable);


    @Query("SELECT t FROM Task t WHERE "
            + "(t.assignedUser.id = :userId) AND "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:priority IS NULL OR t.priority = :priority) AND "
            + "(:dueDate IS NULL OR t.dueDate = :dueDate)")
    Page<Task> findAllByAssignedUserId(
            @Param("userId") Long userId,
            @Param("status") Status status,
            @Param("priority") Priority priority,
            @Param("dueDate") LocalDate dueDate,
            Pageable pageable);

}
