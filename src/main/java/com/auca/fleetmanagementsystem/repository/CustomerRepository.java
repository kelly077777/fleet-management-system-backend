package com.auca.fleetmanagementsystem.repository;

import com.auca.fleetmanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    boolean existsByCustomerCode(String customerCode);

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query ("""
        SELECT DISTINCT t.customer FROM Trip t
        WHERE t.driver.id = :driverId
        """)
    List<Customer> findCustomersOfDriver(@Param("driverId") UUID driverId);
}
