package com.quind.prueba.tecnica.infrastructure.db.repository;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    @Query(value = "SELECT * FROM task WHERE task_code = :taskCode AND begin_date = :endDate", nativeQuery = true)
    Optional<TaskEntity> findByTaskCodeAndEndDate(@Param("taskCode") Long taskCode, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM TaskEntity t WHERE (:status IS NULL OR t.satus = :status) AND " +
            "(:startDate IS NULL OR t.beginDate = :startDate) AND " +
            "(:assignedPerson IS NULL OR t.assignedPerson = :assignedPerson) AND " +
            "(:priority IS NULL OR t.priority = :priority)")
    List<TaskEntity> findByComplexQuery(@Param("status") Status status,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("assignedPerson") String assignedPerson,
                                        @Param("priority") Priority priority);



    List<TaskEntity> findAllByOrderByTaskCodeAsc();
    List<TaskEntity> findAllByOrderByTaskCodeDesc();


}
