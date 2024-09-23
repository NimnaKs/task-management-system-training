package lk.rumex.taskmanagerapp.repository;

import lk.rumex.taskmanagerapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
