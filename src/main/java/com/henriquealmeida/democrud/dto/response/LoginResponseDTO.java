package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record LoginResponseDTO(String token) {
}
