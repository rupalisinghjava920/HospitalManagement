package com.health.management.sevice;


import com.health.management.entity.Doctor;
import com.health.management.entity.PatientBooking;
import com.health.management.entity.User;
import com.health.management.helper.Message;
import com.health.management.repository.DoctorRepository;
import com.health.management.repository.PatientBookRepository;
import com.health.management.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PatientBookingService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientBookRepository patientBookRepository;


    @Autowired
    private DoctorService doctorService;

    //save
    public PatientBooking bookingAppointment(PatientBooking patientBooking,int doctorId){

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Invalid doctor ID: "));
        patientBooking.setDoctor(doctor);
        return patientBookRepository.save(patientBooking);

    }
   //fetch
    public List<PatientBooking> getAllPatientBookings(){
        return patientBookRepository.findAll();
    }


    //accept and reject
     public void updateBookingStatus(Long id, String status) {
        Optional<PatientBooking> bookingOptional = patientBookRepository.findById(id);
        if (bookingOptional.isPresent()) {
            PatientBooking booking = bookingOptional.get();
            booking.setStatus(status);
            patientBookRepository.save(booking);
        }
    }

    //Specialization
    public List<Doctor> getDoctorByPatientSpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    //patientMedicalCondition
    public List<PatientBooking> getPatientBookingsByMedicalCondition(String medicalCondition) {
        return patientBookRepository.findByMedicalCondition(medicalCondition);
    }

    //Single patientBookingId
    public PatientBooking getSinglePatientBooking(Long id) {
        Optional<PatientBooking>patientBooking=patientBookRepository.findById(id);
        if(patientBooking.isPresent()){
            return patientBooking.get();
        }
        throw  new RuntimeException("PatientBooking is not found for id "+id);
    }



}
