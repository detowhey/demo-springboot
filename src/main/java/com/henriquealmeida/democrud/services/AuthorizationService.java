package com.henriquealmeida.democrud.services;

import com.henriquealmeida.democrud.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            log.info("Load user by username");
            return userRepository.findByLogin(username);
        } catch (UsernameNotFoundException e) {
            log.error("User not found by username");
            throw new UsernameNotFoundException("User not found");
        }
    }
}
