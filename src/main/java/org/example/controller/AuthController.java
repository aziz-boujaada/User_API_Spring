package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.example.dto.UserCreateRequest;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateRequest;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequest request) {

        UserResponseDto user = authService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> allUsers() {

        List<UserResponseDto> users = authService.getAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        UserResponseDto user = authService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
             @Valid @RequestBody UserUpdateRequest request,
             @PathVariable("id") Long id
    ) {
        UserResponseDto user = authService.updateUser(request , id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){

        authService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }


}
