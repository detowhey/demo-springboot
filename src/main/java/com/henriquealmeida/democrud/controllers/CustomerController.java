package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.Customer;
import com.henriquealmeida.democrud.dto.request.CustomerRequestDTO;
import com.henriquealmeida.democrud.dto.response.CustomerResponseDTO;
import com.henriquealmeida.democrud.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Customer data endpoint", description = "REST service for searching categories of products data")
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController extends BaseController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Return all customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return all customers",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.ok().body(customerService.findAll().stream()
                .map(customer -> super.convertToType(customer, CustomerResponseDTO.class))
                .toList());
    }

    @Operation(
            summary = "Return customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully search customer by id",
                            content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                super.convertToType(customerService.findById(id), CustomerResponseDTO.class)
        );
    }

    @Operation(
            summary = "Create new customer",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create a customer",
                            content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> insert(@RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customerRequest = super.convertToType(customerRequestDTO, Customer.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerRequest.getId())
                .toUri();

        Customer customer = customerService.insert(customerRequest);
        CustomerResponseDTO userResponse = super.convertToType(customer, CustomerResponseDTO.class);

        return ResponseEntity.created(uri).body(userResponse);
    }

    @Operation(
            summary = "Delete customer by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete customer by id",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().body("Customer with id " + id + " deleted");
    }

    @Operation(
            summary = "Update a customer data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully update customer data",
                            content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))
                    )
            }
    )
    @PutMapping(value = "{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @RequestBody Customer newDataCustomer) {
        return ResponseEntity.ok().body(
                super.convertToType(customerService.update(id, newDataCustomer), CustomerResponseDTO.class)
        );
    }
}
