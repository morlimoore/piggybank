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
        tempUser.setHash(userDTO.getPassword());
        userRepository.save(tempUser);
    }

    public UserDTO loginUser(UserDTO userDTO) {
        UserDTO tempUserDTO = null;
//        System.out.println("email: " + userDTO.getEmail());
//        System.out.println("userInDb " + getUserDetails(userDTO.getEmail()));

        User userFromDB = getUserDetails(userDTO.getEmail());
        if (userFromDB.getEmail().equals(userDTO.getEmail()) &&
                userFromDB.getHash().equals(userDTO.getPassword())) {
            UserDTO userFromDBDTO = modelMapper.map(userFromDB, UserDTO.class);
            tempUserDTO = userFromDBDTO;
            System.out.println(userDTO.getEmail() + " verified");
        } else {
            tempUserDTO = new UserDTO();
            System.out.println(userDTO.getEmail() + " not verified");
        }
        return tempUserDTO;
    }

    @Override
    public User getUserDetails(String email) {
        User tempUser = userRepository.findUserByEmail(email).get();
        return tempUser;
    }
}
