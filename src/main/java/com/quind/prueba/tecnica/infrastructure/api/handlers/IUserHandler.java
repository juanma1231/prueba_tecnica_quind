package com.quind.prueba.tecnica.infrastructure.api.handlers;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.infrastructure.api.dtos.UserDTO;

public interface IUserHandler {
    UserDTO save(UserDTO userDTO);

}
