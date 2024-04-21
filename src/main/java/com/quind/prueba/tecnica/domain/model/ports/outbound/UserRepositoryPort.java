package com.quind.prueba.tecnica.domain.model.ports.outbound;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;

public interface UserRepositoryPort {
    UserLogin save(UserLogin userLogin);

    UserLogin findById(String userName);
}
