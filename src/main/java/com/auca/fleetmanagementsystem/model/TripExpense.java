package com.auca.fleetmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "trip_expense")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TripExpense {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String expenseType;
    private String note;
    private int amount;
    private LocalDate expenseDate;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
    public TripExpense(){

    }
    public UUID getId() {
        return id;
    
}
    
    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getNote() {
        return note;
    }

    
    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
