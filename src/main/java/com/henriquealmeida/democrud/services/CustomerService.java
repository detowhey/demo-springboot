package com.henriquealmeida.democrud.services;

import com.henriquealmeida.democrud.domain.Customer;
import com.henriquealmeida.democrud.exceptions.DataBaseException;
import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import com.henriquealmeida.democrud.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public List<Customer> findAll() {
        log.info("Return all customers");
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        log.info("Find customer by id " + id);
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Customer insert(Customer obj) {
        log.info("Create a new customer");
        return customerRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            log.info("Delete customer by id " + id);
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Empty result error", e);
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity error", e);
            throw new DataBaseException(e.getMessage());
        }
    }

    public Customer update(Long id, Customer newDataCustomer) {
        Customer oldDataCustomer = this.findById(id);
        log.info("Update a customer with id " + id);
        this.updateData(oldDataCustomer, newDataCustomer);
        return customerRepository.save(oldDataCustomer);
    }

    private void updateData(Customer oldDataCustomer, Customer newDataCustomer) {
        oldDataCustomer.setName(newDataCustomer.getName());
        oldDataCustomer.setEmail(newDataCustomer.getEmail());
        oldDataCustomer.setPhone(newDataCustomer.getPhone());
    }
}
