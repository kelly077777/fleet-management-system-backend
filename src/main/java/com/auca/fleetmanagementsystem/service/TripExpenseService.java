package com.auca.fleetmanagementsystem.service; 
import com.auca.fleetmanagementsystem.model.TripExpense;
import com.auca.fleetmanagementsystem.repository.TripExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import com.auca.fleetmanagementsystem.model.Trip;
import com.auca.fleetmanagementsystem.repository.TripRepository;
import com.auca.fleetmanagementsystem.model.TripStatus;
import java.time.LocalDate;
import java.util.Optional;

@Service 

public class TripExpenseService {
    private final TripExpenseRepository tripExpenseRepository;
    private final TripRepository tripRepository;
    public TripExpenseService(TripExpenseRepository tripExpenseRepository, TripRepository tripRepository){
        this.tripExpenseRepository = tripExpenseRepository;
        this.tripRepository = tripRepository;
}

    public String saveTripExpense(UUID tripId, TripExpense tripExpense){
        Optional<Trip> tripOptional = tripRepository.findById(tripId);
        if (tripOptional.isEmpty()){
            return "The trip with that id does not exist";
        }
        Trip trip = tripOptional.get();
        if (trip.getStatus() != TripStatus.ARRIVED){
            return "Trip expenses are only for ARRIVED trips";
        }
        
        tripExpense.setExpenseDate(LocalDate.now());
        tripExpense.setTrip(trip);
        tripExpenseRepository.save(tripExpense);
        return "Trip expense saved successfully";
    }

    public List<TripExpense> getTripExpensesCustomer(String customerCode){
        return tripExpenseRepository.findByCustomerCode(customerCode);
    }

    public List<TripExpense> getAllTripExpenses(){
        return tripExpenseRepository.findAll();
    }

    public String deleteTripExpense(UUID id){
        if (!tripExpenseRepository.existsById(id)){
            return "The trip expense with that id does not exist";
        }
        tripExpenseRepository.deleteById(id);
        return "Trip expense deleted successfully";
    }
    public List<TripExpense> getTripExpensesOfCustomer(String customerCode) {
        return tripExpenseRepository.findByCustomerCode(customerCode);
    }

}
