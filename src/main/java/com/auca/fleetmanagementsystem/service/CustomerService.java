package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Customer;
import com.auca.fleetmanagementsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.auca.fleetmanagementsystem.repository.DriverRepository;


@Service 
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final DriverRepository driverRepository;
    public CustomerService(CustomerRepository customerRepository, DriverRepository driverRepository){
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
    }

    public String saveCustomer(Customer customer) {
        if (customerRepository.existsByCustomerCode(customer.getCustomerCode())){
            return "A customer with that customerCode already exists";
        }
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    public String updateCustomer(UUID id, Customer newData) {
        Optional<Customer> existing = customerRepository.findById(id);
        if (existing.isEmpty()) {
            return "The ctomer with that id does not exist";
        }
        Customer customer = existing.get();
        customer.setFirstName(newData.getFirstName());
        customer.setLastName(newData.getLastName());
        customer.setCustomerCode(newData.getCustomerCode());
        customer.setPhone(newData.getPhone());
        customer.setEmail(newData.getEmail());
        customerRepository.save(customer);
        return "Customer updated successfully";

    }

    public String deleteCustomer(UUID id){
        if (!customerRepository.existsById(id)){
            return "The customer with that id does not exist";
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }
    public List<Customer> getCustomersByLastName(String lastName){
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
    public Optional<List<Customer>> getCustomersOfDriver(UUID driverId){
        if (!driverRepository.existsById(driverId)){
            return Optional.empty();
        }
        return Optional.of(customerRepository.findCustomersOfDriver(driverId));
    }
    
}
