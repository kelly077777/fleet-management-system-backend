package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.TripExpense;
import com.auca.fleetmanagementsystem.service.TripExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trip-expenses")
public class TripExpenseController {
    private final TripExpenseService tripExpenseService;

    public TripExpenseController(TripExpenseService tripExpenseService) {
        this.tripExpenseService = tripExpenseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTripExpense(@RequestParam UUID tripId, @RequestBody TripExpense tripExpense) {
        String message = tripExpenseService.saveTripExpense(tripId, tripExpense);
        if (message.equals("Trip expense saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The trip with that id does not exist")) {

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TripExpense>> getTripExpensesByCustomer(){
       
        return new ResponseEntity<>(tripExpenseService.getAllTripExpenses(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerCode}")
    public ResponseEntity<List<TripExpense>> getTripExpensesByCustomer(@PathVariable String customerCode) {
        
        return new ResponseEntity<>(tripExpenseService.getTripExpensesOfCustomer(customerCode), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTripExpense(@PathVariable UUID id) {
        String message = tripExpenseService.deleteTripExpense(id);
        if (message.equals("Trip expense deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}

