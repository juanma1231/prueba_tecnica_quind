package com.quind.prueba.tecnica.infrastructure.api.mappers.implementation;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.infrastructure.api.dtos.UserDTO;
import com.quind.prueba.tecnica.infrastructure.api.mappers.IUserDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapperImpl implements IUserDtoMapper {
    @Override
    public UserDTO toUserDto(UserLogin userLogin) {
        return new UserDTO(userLogin.getUsername(), userLogin.getPassword());
    }

    @Override
    public UserLogin toUser(UserDTO userDTO) {
        return new UserLogin(userDTO.getUsername(), userDTO.getPassword());
    }
}
