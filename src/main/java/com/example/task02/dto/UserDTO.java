package com.example.task02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
private String fullName;
private String phoneNumber;
private String password;
private String home;
private String city;
private String street;
}
