package com.henriquealmeida.democrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.repositories.UserRepository;
import com.henriquealmeida.democrud.exceptions.DataBaseException;
import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return userRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public User update(Long id, User newDataUser) {
        User oldDataUser = this.findById(id);
        updateData(oldDataUser, newDataUser);
        return userRepository.save(oldDataUser);
    }

    private void updateData(User oldDataUser, User newDataUser) {
        oldDataUser.setName(newDataUser.getName());
        oldDataUser.setEmail(newDataUser.getEmail());
        oldDataUser.setPhone(newDataUser.getPhone());
    }
}
