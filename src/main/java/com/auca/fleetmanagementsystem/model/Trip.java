package com.auca.fleetmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "trip")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Trip{
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate tripDate;
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    private String origin;
    private String destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id")
    private Customer customer;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="driver_id")
    private Driver driver;

    public Trip(){

    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public LocalDate getTripDate(){
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate){
        this.tripDate=tripDate;
    }

    public TripStatus getStatus(){
        return status;
    }

    public void setStatus(TripStatus status){
        this.status = status;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer= customer;
    }
    public Driver getDriver(){
        return driver;
    }

    public void setDriver(Driver driver){
        this.driver = driver;
    }
}
