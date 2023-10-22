package com.henriquealmeida.democrud.services;

import java.util.List;
import java.util.Optional;

import com.henriquealmeida.democrud.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.repositories.CustomerRepository;
import com.henriquealmeida.democrud.exceptions.DataBaseException;
import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Customer insert(Customer obj) {
        return customerRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Customer update(Long id, Customer newDataCustomer) {
        Customer oldDataCustomer = this.findById(id);
        updateData(oldDataCustomer, newDataCustomer);
        return customerRepository.save(oldDataCustomer);
    }

    private void updateData(Customer oldDataCustomer, Customer newDataCustomer) {
        oldDataCustomer.setName(newDataCustomer.getName());
        oldDataCustomer.setEmail(newDataCustomer.getEmail());
        oldDataCustomer.setPhone(newDataCustomer.getPhone());
    }
}
