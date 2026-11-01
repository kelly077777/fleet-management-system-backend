package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Driver;
import com.auca.fleetmanagementsystem.repository.DriverRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service 
public class DriverService {
    private final DriverRepository driverRepository;
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public String saveDriver(Driver driver){
        driverRepository.save(driver);
        return "Driver saved successfully";
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();

    }

    public Optional<Driver> getDriverById(UUID id){
        return driverRepository.findById(id);
    }
    public String updateDriver(UUID id, Driver newData) {
        Optional<Driver> existing = driverRepository.findById(id);
        if (existing.isEmpty()){
            return "The driver with that id does not exist";
        }
        Driver driver = existing.get();
        driver.setFirstName(newData.getFirstName());
        driver.setLastName(newData.getLastName());
        driver.setPhone(newData.getPhone());
        driver.setLicenseNumber(newData.getLicenseNumber());
        driver.setVehicle(newData.getVehicle());
        driver.setLicenseClasses(newData.getLicenseClasses());
        driverRepository.save(driver);
        return "Driver updated successfully";
    }

    public String deleteDriver(UUID id){
        if (!driverRepository.existsById(id)){
            return "The driver with that id does not exist";
        }
        driverRepository.deleteById(id);
        return "Driver deleted successfully";
    }
}
