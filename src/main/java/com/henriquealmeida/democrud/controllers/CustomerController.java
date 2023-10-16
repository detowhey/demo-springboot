package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.Customer;
import com.henriquealmeida.democrud.dto.request.CustomerRequestDTO;
import com.henriquealmeida.democrud.dto.response.CustomerResponseDTO;
import com.henriquealmeida.democrud.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.ok().body(customerService.findAll().stream()
                .map(customer -> super.convertToType(customer, CustomerResponseDTO.class))
                .toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                super.convertToType(customerService.findById(id), CustomerResponseDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> insert(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customerRequest = super.convertToType(customerRequestDTO, Customer.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerRequest.getId()).toUri();

        Customer customer = customerService.insert(customerRequest);
        CustomerResponseDTO userResponse = super.convertToType(customer, CustomerResponseDTO.class);

        return ResponseEntity.created(uri).body(userResponse);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody Customer newDataCustomer) {
        return ResponseEntity.ok().body(
                super.convertToType(customerService.update(id, newDataCustomer), CustomerResponseDTO.class)
        );
    }
}
