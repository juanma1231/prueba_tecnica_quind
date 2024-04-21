package com.quind.prueba.tecnica.infrastructure.db.mappers.implementation;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;

import com.quind.prueba.tecnica.infrastructure.db.entities.task.UserEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.IUsernEntityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityMapperImpl implements IUsernEntityMapper {
    @Override
    public UserEntity toUserEntity(UserLogin userLogin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setLocked(false);
        userEntity.setDisabled(false);
        userEntity.setEmail(userLogin.getUsername());
        userEntity.setUsername(userLogin.getUsername());
        String encodedPassword = encoder.encode(userLogin.getPassword());
        userEntity.setPassword(encodedPassword);
        return userEntity;
    }

    @Override
    public UserLogin toUser(UserEntity user) {
        return new UserLogin(user.getUsername(), user.getPassword(), user.getEmail(), user.getLocked(),user.getDisabled());
    }
}
