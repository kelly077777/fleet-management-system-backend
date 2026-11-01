package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Vehicle;
import com.auca.fleetmanagementsystem.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 





public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public String saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return "Vehicle saved successfully";
    }
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
    public Optional<Vehicle> getVehicleById(UUID id) {
        return vehicleRepository.findById(id);
    }
    public String updateVehicle(UUID id, Vehicle newData){
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if (existing.isEmpty()){
            return "The vehicle with that id does not exist";
        }

        Vehicle vehicle=existing.get();
        vehicle.setFleetNumber(newData.getFleetNumber());
        vehicle.setModel(newData.getModel());
        vehicleRepository.save(vehicle);
        return "Vehicle updated successfullly";
    }

    public String deleteVehicle(UUID id){
        if (!vehicleRepository.existsById(id)){
            return "The vehicle with id does not exist";
        }
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully";
    }
    public List<Vehicle> getFreeVehicles(){
        return vehicleRepository.findFreeVehicles();

    }
}
