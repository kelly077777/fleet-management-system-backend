package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Trip;
import com.auca.fleetmanagementsystem.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 

public class TripService {
    private final TripRepository tripRepository;
    public TripService(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    public String saveTrip(Trip trip){
        tripRepository.save(trip);
        return "Trip saved successfully";
    }
    public List<Trip> getAllTrips(){
        return tripRepository.findAll();
    }
    public Optional<Trip> getTripById(UUID id){
        return tripRepository.findById(id);
    }

    public String updateTrip (UUID id, Trip newData){
        Optional<Trip> existing = tripRepository.findById(id);
        if (existing.isEmpty()){
            return "The trip with that id does not exist";
        }
        Trip trip = existing.get();
        trip.setTripDate(newData.getTripDate());
        trip.setStatus(newData.getStatus());
        trip.setOrigin(newData.getOrigin());
        trip.setDestination(newData.getDestination());
        trip.setCustomer(newData.getCustomer());
        trip.setDriver(newData.getDriver());
        tripRepository.save(trip);
        return "Trip updated successfully";



    }

    public String deleteTrip(UUID id){
        if (!tripRepository.existsById(id)){
            return "The trip with that id does not exist";
        }
        tripRepository.deleteById(id);
        return "Trip deleted successfully";
    }
    
}
