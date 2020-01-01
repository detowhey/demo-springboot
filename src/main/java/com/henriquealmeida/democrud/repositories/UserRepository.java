package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
