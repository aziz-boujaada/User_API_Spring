package org.example.dto;

import org.example.model.User;
import org.example.model.UserRole;

public class UserResponseDto {

    private Long id ;
    private String name ;
    private String email ;
    private String phone ;
    private UserRole role ;
    private boolean active ;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.active = user.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
