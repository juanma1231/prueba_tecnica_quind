package com.quind.prueba.tecnica.infrastructure.db.repository;

import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
}
