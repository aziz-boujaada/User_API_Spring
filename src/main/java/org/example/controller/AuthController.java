package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.UserCreateRequest;
import org.example.dto.UserResponseDto;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public  AuthController(AuthService authService){
        this.authService = authService ;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequest request){

        UserResponseDto user = authService.createUser(request);

        return ResponseEntity
                .status( HttpStatus.CREATED)
                .body(user);
    }

}
