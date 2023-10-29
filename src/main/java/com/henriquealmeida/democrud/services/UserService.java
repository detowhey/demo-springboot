package com.henriquealmeida.democrud.services;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.exceptions.UserUnavailableLoginException;
import com.henriquealmeida.democrud.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        if (userRepository.findByLogin(user.getUsername()) != null) {
            log.error("User unavailable with this data");
            throw new UserUnavailableLoginException("User unavailable");
        }
        log.info("Create a new user");
        userRepository.save(user);
    }
}
