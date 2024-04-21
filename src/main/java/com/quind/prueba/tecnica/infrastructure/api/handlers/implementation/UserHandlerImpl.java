package com.quind.prueba.tecnica.infrastructure.api.handlers.implementation;

import com.quind.prueba.tecnica.domain.model.ports.inbound.UserUseCasePort;
import com.quind.prueba.tecnica.infrastructure.api.dtos.UserDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.IUserHandler;
import com.quind.prueba.tecnica.infrastructure.api.mappers.IUserDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserHandlerImpl implements IUserHandler {
    private final IUserDtoMapper iUserDtoMapper;
    private final UserUseCasePort userUseCasePort;

    public UserHandlerImpl(IUserDtoMapper iUserDtoMapper, UserUseCasePort userUseCasePort) {
        this.iUserDtoMapper = iUserDtoMapper;
        this.userUseCasePort = userUseCasePort;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return iUserDtoMapper.toUserDto(userUseCasePort.createUser(iUserDtoMapper.toUser(userDTO)));
    }
}
