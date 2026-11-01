package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Driver;
import com.auca.fleetmanagementsystem.repository.DriverRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.Vehicle;
import com.auca.fleetmanagementsystem.model.LicenseClass;
import com.auca.fleetmanagementsystem.repository.LicenseClassRepository;
import com.auca.fleetmanagementsystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service 
public class DriverService {
    private final DriverRepository driverRepository;
    private final LicenseClassRepository licenseClassRepository;
    private final VehicleRepository vehicleRepository;

    public DriverService(DriverRepository driverRepository, LicenseClassRepository licenseClassRepository, VehicleRepository vehicleRepository){
        this.driverRepository = driverRepository;
        this.licenseClassRepository = licenseClassRepository;
        this.vehicleRepository = vehicleRepository;
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
    public List<Driver> getDriversByLicenseClass(String name){
        return driverRepository.findDriversByLicenseClassName(name);
    }
    public String addLicenseClassToDriver(UUID driverId, UUID licenseClassId){
        Optional<Driver> driverOptinonal = driverRepository.findById(driverId);
        if (driverOptinonal.isEmpty()){
            return "The driver with that id does not exist";
        }
        Optional<LicenseClass> licenseClassOptinal = licenseClassRepository.findById(licenseClassId);
        if (licenseClassOptinal.isEmpty()){
            return "That license class does not exist";
        }
        Driver driver = driverOptinonal.get();
        LicenseClass licenseClass = licenseClassOptinal.get();
        if (driver.getLicenseClasses().contains(licenseClass)){
            return "The driver already has that license class";
        }
        driver.getLicenseClasses().add(licenseClass);
        driverRepository.save(driver);
        return "License class added to the driver";
    }
    public String changeVehicle(UUID driverId, int fleetNumber){
        Optional<Driver> driverOptional = driverRepository.findById(driverId);
        if (driverOptional.isEmpty()){
            return "The driver with that id does not exist";
        }
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByFleetNumber(fleetNumber);
        if (vehicleOptional.isEmpty()){
            return "The vehicle with that fleet number does not exist";
        }
        Driver driver = driverOptional.get();
        Vehicle vehicle = vehicleOptional.get();
        boolean alreadyHisVehicle = driver.getVehicle() != null && driver.getVehicle().getId().equals(vehicle.getId()); 
        if (!alreadyHisVehicle && driverRepository.existsByVehicle(vehicle)) {
            return "That vehicle is taken by another driver";
        }
        driver.setVehicle(vehicle);
        driverRepository.save(driver);
        return "Vehicle changed successfully";
    }
}
