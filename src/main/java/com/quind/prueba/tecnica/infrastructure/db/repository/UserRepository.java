package com.quind.prueba.tecnica.infrastructure.db.repository;

import com.quind.prueba.tecnica.infrastructure.db.entities.task.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}