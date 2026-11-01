package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.Driver;
import com.auca.fleetmanagementsystem.service.DriverService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController 
@RequestMapping ("/api/drivers")
public class DriverController {
    private final DriverService driverService;
    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveDriver(@RequestBody Driver driver){
        String message = driverService.saveDriver(driver);
        return new ResponseEntity<>(message,HttpStatus.CREATED);

        
        
    }

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable UUID id){
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()){
            return new ResponseEntity<>(driver.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("The driver with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDriver(@PathVariable UUID id, @RequestBody Driver driver){
        String message = driverService.updateDriver(id, driver);
        if (message.equals("Driver updated successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);

        }else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable UUID id){
        String message = driverService.deleteDriver(id);
        if (message.equals("Driver deleted successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping ("/by-license-class")
    public ResponseEntity<List<Driver>> getDriversByLicenseClass(@RequestParam String name){
        return new ResponseEntity<>(driverService.getDriversByLicenseClass(name), HttpStatus.OK);
    }
    @PutMapping ("/{driverId}/license-classes/{licenseClassId}")
    public ResponseEntity<String> addLicenseClass(@PathVariable UUID driverId, @PathVariable UUID licenseClassId){
        String message = driverService.addLicenseClassToDriver(driverId, licenseClassId);
        if (message.equals("License class added to the driver")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }else if (message.equals("The driver already has that license class")){
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    } 

    @PatchMapping  ("/{driverId}/vehicle")
    public ResponseEntity<String> changeVehicle(@PathVariable UUID driverId, @RequestParam int fleetNumber){
        String message = driverService.changeVehicle(driverId, fleetNumber);
        if (message.equals("Vehicle changed successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }else if (message.equals("That vehicle is taken by another driver")){
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
}
