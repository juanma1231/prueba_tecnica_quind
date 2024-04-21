package com.quind.prueba.tecnica.domain.model.ports.inbound;

import com.quind.prueba.tecnica.domain.model.models.UserLogin;

public interface UserUseCasePort {
    UserLogin createUser(UserLogin userLogin);
}
