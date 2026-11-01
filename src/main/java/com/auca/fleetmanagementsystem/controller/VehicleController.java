package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.Vehicle;
import com.auca.fleetmanagementsystem.service.VehicleService;
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

@RestController 
@RequestMapping ("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping ("/save")
    public ResponseEntity<String> saveVehicle(@RequestBody Vehicle vehicle){
        String message = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable UUID id){
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()){
            return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The vehicle with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable UUID id, @RequestBody Vehicle vehicle){
        String message = vehicleService.updateVehicle(id, vehicle);
        if (message.equals("Vehicle updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteVehicle(@PathVariable UUID id){
        String message = vehicleService.deleteVehicle(id);
        if (message.equals("Vehicle deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
