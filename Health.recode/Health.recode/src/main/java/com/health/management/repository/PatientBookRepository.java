package com.health.management.repository;

import com.health.management.entity.PatientBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientBookRepository extends JpaRepository<PatientBooking,Long> {
    List<PatientBooking> findByDoctorId(Long doctorId);
    
}
