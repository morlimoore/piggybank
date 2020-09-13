package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.UserRepository;
import com.morlimoore.piggybank.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public  UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User tempUser = modelMapper.map(userDTO, User.class);
        userRepository.save(tempUser);
    }
}
