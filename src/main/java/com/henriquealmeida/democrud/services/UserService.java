package com.henriquealmeida.democrud.services;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.exceptions.UserUnavailableLoginException;
import com.henriquealmeida.democrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        if (userRepository.findByLogin(user.getUsername()) != null)
            throw new UserUnavailableLoginException("User unavailable");
        userRepository.save(user);
    }
}
