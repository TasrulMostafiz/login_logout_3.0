package com.example.login_logout_30;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customuser")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long userid;

    @NotBlank(message = "name is required!")
    private String name;
    @NotBlank(message = "email is required!")
    private String email;
    @NotBlank(message = "pass is required!")
    private String pass;
    @NotBlank(message = "role is required!")
    private String roles;




}