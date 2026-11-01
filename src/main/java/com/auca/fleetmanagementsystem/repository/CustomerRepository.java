package com.auca.fleetmanagementsystem.repository;

import com.auca.fleetmanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    boolean existsByCustomerCode(String customerCode);

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    
}
