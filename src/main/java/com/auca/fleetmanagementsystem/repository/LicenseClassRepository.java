package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.LicenseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LicenseClassRepository extends JpaRepository<LicenseClass,UUID> {

    
}