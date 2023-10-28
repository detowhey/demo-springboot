package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.dto.request.AuthenticationRequestDTO;
import com.henriquealmeida.democrud.dto.request.RegisterRequestDTO;
import com.henriquealmeida.democrud.dto.response.LoginResponseDTO;
import com.henriquealmeida.democrud.exceptions.error.StandardError;
import com.henriquealmeida.democrud.services.TokenService;
import com.henriquealmeida.democrud.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Authentication endpoint for generate token and login")
@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Operation(
            operationId = "login",
            summary = "Generate token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully generate token",
                            content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized request",
                            content = @Content(schema = @Schema(implementation = StandardError.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestHeader(name = "api_key", required = false) @RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationRequestDTO.login(), authenticationRequestDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(
            summary = "Register new user in the application",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully create a new user",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequestDTO.password());
        User newUser = new User(registerRequestDTO.login(), encryptedPassword, registerRequestDTO.role());
        this.userService.saveUser(newUser);
        return ResponseEntity.ok().body("New user registered");
    }
}
