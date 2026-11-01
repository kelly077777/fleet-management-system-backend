package com.auca.fleetmanagementsystem.repository;
import com.auca.fleetmanagementsystem.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    @Query("""
        SELECT d from Driver d
        JOIN d.licenseClasses l
        WHERE LOWER(l.name) = LOWER(:name)""")
    List<Driver> findDriversByLicenseClassName(@Param("name") String name);
}
