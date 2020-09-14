package com.morlimoore.piggybank.services;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.User;

public interface UserService {

    void registerUser(UserDTO userDTO);

    UserDTO loginUser(UserDTO userDTO);

    User getUserDetails(String email);
}
