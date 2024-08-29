package com.health.management.sevice;

import com.health.management.entity.Doctor;
import com.health.management.entity.PatientBooking;
import com.health.management.repository.DoctorRepository;
import com.health.management.repository.PatientBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

//    public Optional<Doctor> findById(int id) {
//        return doctorRepository.findById(id);
//    }


}
