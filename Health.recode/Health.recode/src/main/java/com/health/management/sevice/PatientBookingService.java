package com.health.management.sevice;


import com.health.management.entity.PatientBooking;
import com.health.management.repository.PatientBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PatientBookingService {

    @Autowired
    private PatientBookRepository patientBookRepository;


    public PatientBooking bookingAppointment(PatientBooking patientBooking){
        return patientBookRepository.save(patientBooking);

    }

    public List<PatientBooking> getAllPatientBookings(){
        return patientBookRepository.findAll();
    }

//    public PatientBooking getPatientBookingById(Long id){
//        return patientRepository.findById(id).orElse(null);
//    }


    public List<PatientBooking> getBookingsByDoctorId(Long doctorId) {
        return patientBookRepository.findByDoctorId(doctorId);
    }

}
