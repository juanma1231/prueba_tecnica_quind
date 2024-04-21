package com.quind.prueba.tecnica.infrastructure.api.mappers;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.infrastructure.api.dtos.UserDTO;

public interface IUserDtoMapper {
    UserDTO toUserDto(UserLogin userLogin);

    UserLogin toUser(UserDTO userDTO);
}
