package com.quind.prueba.tecnica.infrastructure.db.adapters;

import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TaskDbAdapter implements TaskRepositoryPort {
}
