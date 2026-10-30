package com.auca.fleetmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;
@Entity 
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String customerCode;

    private String phone;

    private String email;
    public Customer() {

    }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id=id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName= lastName;
    }

    public String getCustomerCode(){
        return customerCode;
    }

    public void setCustomerCode(String customerCode){
        this.customerCode= customerCode;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone= phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email= email;
    }
}

