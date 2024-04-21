package com.quind.prueba.tecnica.domain.usecase;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;
import com.quind.prueba.tecnica.domain.model.ports.inbound.UserUseCasePort;
import com.quind.prueba.tecnica.domain.model.ports.outbound.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService, UserUseCasePort {
    private final UserRepositoryPort userRepository;

    @Autowired
    public UserSecurityService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserLogin userLogin = this.userRepository.findById(username);

        return User.builder()
                .username(userLogin.getUsername())
                .password(userLogin.getPassword())
                .roles("ADMIN")
                .accountLocked(userLogin.getLocked())
                .disabled(userLogin.getDisabled())
                .build();
    }

    @Override
    public UserLogin createUser(UserLogin userLogin) {
        return userRepository.save(userLogin);
    }
}