package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.Trip;
import com.auca.fleetmanagementsystem.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.TripStatus;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController 
@RequestMapping ("/api/trips")
public class TripController {
    private final TripService tripService;
    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveTrip(@RequestBody Trip trip) {
        String message = tripService.saveTrip(trip);
        if (message.equals("Trip saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Driver is already on a trip on that date")){
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
       
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getAllTrips(){
        return new ResponseEntity<>(tripService.getAllTrips(), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable UUID id){
        Optional<Trip> trip = tripService.getTripById(id);
        if (trip.isPresent()){
            return new ResponseEntity<>(trip.get(), HttpStatus.OK);

        }else {
            return new ResponseEntity<>("The trip with that id does not exist", HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateTrip(@PathVariable UUID id, @RequestBody Trip trip){
        String message = tripService.updateTrip(id, trip);
        if (message.equals("Trip updated sucessfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    } 

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable UUID id) {
        String message = tripService.deleteTrip(id);
        if (message.equals("Trip deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else if (message.equals("That trip has trip expenses, delete them first")){
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping ("/by-status")
    public ResponseEntity<List<Trip>> getTripsByStatus(@RequestParam TripStatus status){
        return new ResponseEntity<>(tripService.getTripsByStatus(status), HttpStatus.OK);
    }
    @GetMapping ("/between")
    public ResponseEntity<List<Trip>> getTripsBetween(@RequestParam String start,@RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(tripService.getTripsBetween(startDate, endDate), HttpStatus.OK );
    }
    @PatchMapping ("/{id}/status")
    public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam TripStatus status) {
        String message = tripService.changeStatus(id,status);
        if (message.equals("Trip status updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The trip with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

}