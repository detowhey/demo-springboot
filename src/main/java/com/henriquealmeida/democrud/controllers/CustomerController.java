package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.Customer;
import com.henriquealmeida.democrud.dto.request.CustomerRequestDTO;
import com.henriquealmeida.democrud.dto.response.UserResponseDTO;
import com.henriquealmeida.democrud.services.CustomerService;
import com.henriquealmeida.democrud.util.Convert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class CustomerController {

    private final CustomerService customerService;
    private final Convert convert = Convert.getInstance();

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok().body(customerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                convert.convertToType(customerService.findById(id), UserResponseDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> insert(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customerRequest = convert.convertToType(customerRequestDTO, Customer.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerRequest.getId()).toUri();

        Customer customer = customerService.insert(customerRequest);
        UserResponseDTO userResponse = convert.convertToType(customer, UserResponseDTO.class);

        return ResponseEntity.created(uri).body(userResponse);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer newDataCustomer) {
        return ResponseEntity.ok().body(customerService.update(id, newDataCustomer));
    }
}
