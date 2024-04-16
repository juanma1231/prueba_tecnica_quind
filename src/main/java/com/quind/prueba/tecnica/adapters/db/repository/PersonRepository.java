package com.quind.prueba.tecnica.adapters.db.repository;

import com.quind.prueba.tecnica.adapters.db.entities.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
}
