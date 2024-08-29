package com.health.management.repository;

import com.health.management.entity.Doctor;
import com.health.management.entity.PatientBooking;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientBookRepository extends JpaRepository<PatientBooking, Long> {
    Optional<PatientBooking> findById(Long id);


    List<PatientBooking> findByMedicalCondition(String medicalCondition);

}
