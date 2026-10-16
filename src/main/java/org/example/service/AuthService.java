package org.example.service;

import org.example.dto.UserCreateRequest;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateRequest;
import org.example.model.User;
import org.example.model.UserRole;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto findById(Long id){

       User user = userRepository.findById(id)
                .orElseThrow( ()->
                        new IllegalArgumentException("User with ID" + " " + id + " " + "Not found"));


        return new UserResponseDto(user);
    }

    public UserResponseDto updateUser(UserUpdateRequest request , Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User with ID" + " " + id + " " + "Not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User with ID" + " " + id + " " + "Not found"));

        userRepository.delete(user);
    }
}
