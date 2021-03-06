package com.morlimoore.piggybank.services.impl;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.DTO.UserFromDbDTO;
import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.UserRepository;
import com.morlimoore.piggybank.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public  UserServiceImpl(UserRepository userRepository,
                            ModelMapper modelMapper,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User tempUser = modelMapper.map(userDTO, User.class);
        tempUser.setActive(1);
        tempUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        tempUser.setSocial_password(passwordEncoder.encode("social123"));
        tempUser.setPermissions("ACCESS_USER");
        tempUser.setRoles("USER");
        userRepository.save(tempUser);
    }

//    public UserDTO loginUser(UserDTO userDTO) {
//        UserDTO tempUserDTO = null;
////        System.out.println("email: " + userDTO.getEmail());
////        System.out.println("userInDb " + getUserDetails(userDTO.getEmail()));
//
//        UserFromDbDTO userFromDB = getUser(userDTO.getEmail());
//        if (userFromDB.getEmail().equals(userDTO.getEmail()) &&
//                userFromDB.getPassword().equals(userDTO.getPassword())) {
//            UserDTO userFromDBDTO = modelMapper.map(userFromDB, UserDTO.class);
//            tempUserDTO = userFromDBDTO;
//            System.out.println(userDTO.getEmail() + " verified");
//        } else {
//            tempUserDTO = new UserDTO();
//            System.out.println(userDTO.getEmail() + " not verified");
//        }
//        return tempUserDTO;
//    }

    @Override
    public UserFromDbDTO getUser(String email) {
        User tempUser = userRepository.findUserByEmail(email).get();
        return modelMapper.map(tempUser, UserFromDbDTO.class);
    }
}
