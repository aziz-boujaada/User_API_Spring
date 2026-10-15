package org.example.service;

import org.example.dto.UserCreateRequest;
import org.example.dto.UserResponseDto;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public  AuthService(UserRepository userRepository , PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserCreateRequest request){

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        // make password encoded after stored
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(UserRole.USER);
        user.setActive(true);

         userRepository.save(user);
         return  new UserResponseDto(user);
    }
}
