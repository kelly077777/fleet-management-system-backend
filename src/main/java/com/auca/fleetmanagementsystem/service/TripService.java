package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.Trip;
import com.auca.fleetmanagementsystem.repository.TripRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.TripStatus;
import java.time.LocalDate;

@Service 

public class TripService {
    private final TripRepository tripRepository;
    public TripService(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    public String saveTrip(Trip trip){
        if (trip.getTripDate().isBefore(LocalDate.now())){
            return "Trip date cannot be in the past";
        }
        if (trip.getDriver() != null && tripRepository.existsByDriverIdAndTripDateAndStatusNot(trip.getDriver().getId(), trip.getTripDate(), TripStatus.ABORTED)){
            return "Driver is already on a trip on that date";
        }
        trip.setStatus(TripStatus.REQUESTED);
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
    
    public List<Trip> getTripsByStatus(TripStatus status) {
        return tripRepository.findByStatusOrderByTripDateAsc(status);
    }
    public List<Trip> getTripsBetween(LocalDate start, LocalDate end){
        return tripRepository.findByTripDateBetweenOrderByTripDateAsc(start, end);
    }
    public String changeStatus(UUID id, TripStatus newStatus){
        Optional<Trip> existing = tripRepository.findById(id);
        if (existing.isEmpty()){
            return "The trip with that id does not exist";
        }
        Trip trip = existing.get();
        TripStatus current = trip.getStatus();
        boolean allowed = false; 
        if (current == TripStatus.REQUESTED){
            allowed = newStatus == TripStatus.EN_ROUTE || newStatus == TripStatus.ABORTED;
        }else if (current == TripStatus.EN_ROUTE){
            allowed = newStatus == TripStatus.ARRIVED || newStatus == TripStatus.ABORTED;
        }
        if (!allowed){
            return " Cannot change status from " + current + " to " + newStatus;
        }
        trip.setStatus(newStatus);
        tripRepository.save(trip);
        return "Trip status updated successfully";
    }
}
