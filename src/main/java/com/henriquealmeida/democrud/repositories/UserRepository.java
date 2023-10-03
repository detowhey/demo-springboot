package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
