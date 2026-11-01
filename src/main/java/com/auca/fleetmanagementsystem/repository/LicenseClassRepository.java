package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.LicenseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LicenseClassRepository extends JpaRepository<LicenseClass,UUID> {

    @Query("""
        SELECT l FROM LicenseClass l
        WHERE l.drivers IS EMPTY
        """)
        List<LicenseClass> findUnusedLicenseClasses();
}