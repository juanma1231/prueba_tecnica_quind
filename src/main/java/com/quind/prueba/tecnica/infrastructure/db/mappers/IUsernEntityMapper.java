package com.quind.prueba.tecnica.infrastructure.db.mappers;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.UserEntity;

public interface IUsernEntityMapper {
    UserEntity toUserEntity(UserLogin userLogin);

    UserLogin toUser(UserEntity user);
}
