package com.auca.fleetmanagementsystem.repository;

import com.auca.fleetmanagementsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    @Query("""
        SELECT v FROM Vehicle v
        WHERE NOT EXISTS (SELECT d from Driver d WHERE d.vehicle = v)
        ORDER BY v.fleetNumber
        """)

    List<Vehicle> findFreeVehicles();
    Optional<Vehicle> findByFleetNumber(int fleetNumber);
}

