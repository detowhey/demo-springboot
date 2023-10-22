package com.henriquealmeida.democrud.dto.request;

import com.henriquealmeida.democrud.domain.enu.UserRole;

public record RegisterRequestDTO(String login, String password, UserRole role) {
}
