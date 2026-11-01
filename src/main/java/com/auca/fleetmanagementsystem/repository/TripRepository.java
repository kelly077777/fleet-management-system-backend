package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID>{
    
}
