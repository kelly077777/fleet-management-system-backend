package com.auca.fleetmanagementsystem.controller;
import com.auca.fleetmanagementsystem.model.LicenseClass;
import com.auca.fleetmanagementsystem.service.LicenseClassService;
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
@RequestMapping ("/api/license-classes")


public class LicenseClassController { 

    private final LicenseClassService licenseClassService;
    public LicenseClassController(LicenseClassService licenseClassService){
        this.licenseClassService = licenseClassService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveLicenseClass(@RequestBody LicenseClass licenseClass){
        String message = licenseClassService.saveLicenseClass(licenseClass);
        return  new ResponseEntity<>(message, HttpStatus.CREATED);

    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getLicenseClassById(@PathVariable UUID id){
        Optional<LicenseClass> licenseClass = licenseClassService.getLicenseClassById(id);
        if (licenseClass.isPresent()){
            return new ResponseEntity<>(licenseClass.get(), HttpStatus.OK);

        }else {
            return new ResponseEntity<>("The license class with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLicenseClass(@PathVariable UUID id, @RequestBody LicenseClass licenseClass){
        String message = licenseClassService.updateLicenseClass(id, licenseClass);
        if (message.equals("License class updated successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteLicenseClass(@PathVariable UUID id){
        String message = licenseClassService.deleteLicenseClass(id);
        if (message.equals("License class deleted successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
    }
}
