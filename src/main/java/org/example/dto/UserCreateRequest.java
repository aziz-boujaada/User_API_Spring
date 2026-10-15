package org.example.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {

   @NotBlank(message = "le nom est obligatoire")
   private String name;

   @NotBlank(message = "email est obligatoire ")
   @Email(message = "email est invalid")
   private String email;

   @NotBlank(message = "numero de telephone est obligatoire ")
   @Pattern(regexp = "^[0-9]{8,15}$" , message = "Le numéro doit contenir entre 8 et 15 chiffres")
   private String phone ;

   @NotBlank(message = "mot de pass est obligatoire ")
   @Size(min =  6 , message = "la tail de mot de pass doit etre 6 character minimun")
   private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
