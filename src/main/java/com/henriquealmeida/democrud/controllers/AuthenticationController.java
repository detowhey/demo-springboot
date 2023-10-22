package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.dto.request.AuthenticationRequestDTO;
import com.henriquealmeida.democrud.dto.request.RegisterRequestDTO;
import com.henriquealmeida.democrud.dto.response.LoginResponseDTO;
import com.henriquealmeida.democrud.services.TokenService;
import com.henriquealmeida.democrud.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationRequestDTO.login(), authenticationRequestDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.password());
        User newUser = new User(registerRequestDTO.login(), encryptedPassword, registerRequestDTO.role());
        this.userService.saveUser(newUser);
        return ResponseEntity.ok().body("New user registered");
    }
}
