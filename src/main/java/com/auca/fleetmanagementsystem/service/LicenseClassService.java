package com.auca.fleetmanagementsystem.service;
import com.auca.fleetmanagementsystem.model.LicenseClass;
import com.auca.fleetmanagementsystem.repository.LicenseClassRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class LicenseClassService {
    private final LicenseClassRepository licenseClassRepository;
    public LicenseClassService(LicenseClassRepository licenseClassRepository){
        this.licenseClassRepository = licenseClassRepository;
    }

    public String saveLicenseClass(LicenseClass licenseClass){
        licenseClassRepository.save(licenseClass);
        return "License class saved successfully";
    }

    public List<LicenseClass> getAllLicenseClasses(){
        return licenseClassRepository.findAll();

    }

    public Optional<LicenseClass> getLicenseClassById(UUID id) {
        return licenseClassRepository.findById(id);

    }
    public String updateLicenseClass(UUID id, LicenseClass newData){
        Optional<LicenseClass> existing = licenseClassRepository.findById(id);
        if (existing.isEmpty()) {
            return "The license class with that id does not exist";
        }
    
    LicenseClass licenseClass = existing.get();
    licenseClass.setName(newData.getName());
    licenseClassRepository.save(licenseClass);
    return "License class updated successfully";

    }

    public String deleteLicenseClass(UUID id){
        if (!licenseClassRepository.existsById(id)){
            return "The license class with that id does noot exist";
        }
        licenseClassRepository.deleteById(id);
        return "License class deleted successfully";
    }
    public List<LicenseClass> getUnusedLicenseClasses(){
        return licenseClassRepository.findUnusedLicenseClasses();
    }

}
