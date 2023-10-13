package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.dto.request.UserRequestDTO;
import com.henriquealmeida.democrud.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserRequestDTO userRequestDTO) {
        User user = this.dtoToUser(userRequestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(userService.insert(user));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User newDataUser) {
        return ResponseEntity.ok().body(userService.update(id, newDataUser));
    }

    private User dtoToUser(UserRequestDTO userRequestDTO) {
        return new ModelMapper().map(userRequestDTO, User.class);
    }
}
