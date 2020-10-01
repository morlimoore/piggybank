package com.morlimoore.piggybank.security;

import com.morlimoore.piggybank.entities.User;
import com.morlimoore.piggybank.repositories.UserRepository;
import com.morlimoore.piggybank.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s).get();
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
