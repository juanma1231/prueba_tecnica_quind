package com.quind.prueba.tecnica.infrastructure.api.controller;

import com.quind.prueba.tecnica.config.JwtUtils;
import com.quind.prueba.tecnica.domain.usecase.UserSecurityService;
import com.quind.prueba.tecnica.infrastructure.api.controller.response.AutResponse;
import com.quind.prueba.tecnica.infrastructure.api.controller.response.ResponseController;
import com.quind.prueba.tecnica.infrastructure.api.dtos.UserDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.IUserHandler;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final IUserHandler iUserHandler;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtil, IUserHandler iUserHandler) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.iUserHandler = iUserHandler;

    }

    @PostMapping("/login")
    public ResponseEntity<AutResponse> login(@RequestBody UserDTO loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        String jwt = this.jwtUtil.create(loginDto.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new AutResponse("Autorizado exitosamente",HttpStatus.OK.value(),jwt));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseController> create(@RequestBody UserDTO loginDto) {
        UserDTO userDTO= iUserHandler.save(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Usuario creado con exito con el username: "+userDTO.getUsername(),HttpStatus.CREATED.value()));
    }

}