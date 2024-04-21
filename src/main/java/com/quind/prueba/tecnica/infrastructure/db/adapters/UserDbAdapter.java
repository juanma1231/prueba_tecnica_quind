package com.quind.prueba.tecnica.infrastructure.db.adapters;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.domain.model.ports.outbound.UserRepositoryPort;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.UserEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.IUsernEntityMapper;
import com.quind.prueba.tecnica.infrastructure.db.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserDbAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;
    private final IUsernEntityMapper iUsernEntityMapper;

    private static final Logger log = LogManager.getLogger(UserDbAdapter.class);

    public UserDbAdapter(UserRepository userRepository, IUsernEntityMapper iUsernEntityMapper) {
        this.userRepository = userRepository;
        this.iUsernEntityMapper = iUsernEntityMapper;
    }

    @Override
    public UserLogin save(UserLogin userLogin) {
        log.info("se guardó un usuario");
        return iUsernEntityMapper.toUser(userRepository.save(iUsernEntityMapper.toUserEntity(userLogin)));
    }

    @Override
    public UserLogin findById(String userName) {
        log.info("se consultó un usuario");
        Optional<UserEntity> user = userRepository.findById(userName);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Usuario no encontrado con este id: "+userName);

        }else return iUsernEntityMapper.toUser(user.get());

    }
}
