package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.TripExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.Trip;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripExpenseRepository extends JpaRepository<TripExpense, UUID> { 

    boolean existsByTrip(Trip trip);
    @Query("""
        SELECT d FROM TripExpense d
        WHERE d.trip.customer.customerCode= :customerCode
        ORDER BY d.expenseDate DESC
        """)
    List<TripExpense> findByCustomerCode(@Param("customerCode") String customerCode);
}
