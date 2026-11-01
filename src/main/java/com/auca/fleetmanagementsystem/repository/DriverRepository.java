package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    
}
