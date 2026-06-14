package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
