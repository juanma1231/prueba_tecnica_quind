package com.quind.prueba.tecnica.adapters.db.repository;

import com.quind.prueba.tecnica.adapters.db.entities.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,String> {
}
