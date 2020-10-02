//package com.morlimoore.piggybank.services;
//
//import com.morlimoore.piggybank.entities.User;
//import com.morlimoore.piggybank.repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class DbInit implements CommandLineRunner {
//
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        User kaito = new User("kaito", "echendu", "kaito@gmail.com",
//                passwordEncoder.encode("kaito123"),"2019/05/17", "USER", "ACCESS_USER");
//
//        User muna = new User("muna", "lawrence", "muna@gmail.com",
//                passwordEncoder.encode("muna123"),"2018/04/19", "USER", "ACCESS_USER");
//
//        User morlimoore = new User("ike", "echendu", "ike@gmail.com",
//                passwordEncoder.encode("ike123"),"2000/06/16", "ADMIN", "ACCESS_ADMIN");
//
//        List<User> users = Arrays.asList(kaito, muna, morlimoore);
//
//        userRepository.saveAll(users);
//    }
//}
