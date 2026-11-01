package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.TripStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;;

public interface TripRepository extends JpaRepository<Trip, UUID>{
    
    List<Trip> findByStatusOrderByTripDateAsc(TripStatus status);
    List<Trip> findByTripDateBetweenOrderByTripDateAsc(LocalDate start, LocalDate end);
    boolean existsByDriverIdAndTripDateAndStatusNot(UUID driverId, LocalDate tripDate, TripStatus status);
    @Query ("""
        SELECT t.status, COUNT(t) FROM Trip t
        GROUP BY t.status
        """)
        List<Object[]> countTripsByStatus();
        @Modifying 
        @Query ("""
            UPDATE Trip t SET t.status = :aborted
            WHERE t.driver.id = :driverId
            AND t.tripDate = :day
            AND t.status <> :arrived
            """)
            int cancelDriverTripsOnDay(@Param ("driverId") UUID driverId,
            @Param ("day") LocalDate day,
            @Param ("aborted") TripStatus aborted,
            @Param ("arrived") TripStatus arrived
        );

}
