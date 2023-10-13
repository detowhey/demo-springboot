package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.dto.request.UserRequestDTO;
import com.henriquealmeida.democrud.dto.response.UserResponseDTO;
import com.henriquealmeida.democrud.services.UserService;
import com.henriquealmeida.democrud.util.Convert;
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
    private final Convert convert = Convert.getInstance();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                convert.convertToType(userService.findById(id), UserResponseDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO userRequestDTO) {
        User userRequest = convert.convertToType(userRequestDTO, User.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRequest.getId()).toUri();

        User user = userService.insert(userRequest);
        UserResponseDTO userResponse = convert.convertToType(user, UserResponseDTO.class);

        return ResponseEntity.created(uri).body(userResponse);
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
