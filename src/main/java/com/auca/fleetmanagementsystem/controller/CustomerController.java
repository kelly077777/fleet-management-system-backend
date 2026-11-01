package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.Customer;
import com.auca.fleetmanagementsystem.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestParam;

@RestController 
@RequestMapping("/api/customers")

public class CustomerController {
    private final CustomerService customerService;
    
    
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
        String message = customerService.saveCustomer(customer);
        if (message.equals("Customer saved successfully")){
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()){
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("The customer with that id does not exist", HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable UUID id, @RequestBody Customer customer){
        String message = customerService.updateCustomer(id, customer);
        if (message.equals("Customer updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id){
        String message = customerService.deleteCustomer(id);
        if (message.equals("Customer delted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Customer>> getCustomersByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(customerService.getCustomersByLastName(lastName), HttpStatus.OK);
    }

}
