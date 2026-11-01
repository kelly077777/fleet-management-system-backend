package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.TripStatus;
import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, UUID>{
    
    List<Trip> findByStatusOrderByTripDateAsc(TripStatus status);
    List<Trip> findByTripDateBetweenOrderByTripDateAsc(LocalDate start, LocalDate end);
    boolean existsByDriverIdAndTripDateAndStatusNot(UUID driverId, LocalDate tripDate, TripStatus status);
}
