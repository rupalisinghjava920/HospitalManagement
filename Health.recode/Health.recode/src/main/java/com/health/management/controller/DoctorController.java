package com.health.management.controller;

import com.health.management.entity.Doctor;
import com.health.management.entity.PatientBooking;
import com.health.management.sevice.DoctorService;
import com.health.management.sevice.PatientBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientBookingService patientBookingService;
    @GetMapping("/patientAppointment")
    public String createDoctorForm(Model model)
    {
        model.addAttribute("doctor",new Doctor());
        return "doctor/patientAppointment";
    }

    @PostMapping("/do_viewDoctor")
    public String saveDoctor(@ModelAttribute Doctor doctor){
        doctorService.saveDoctor(doctor);
        return "doctor/patientAppointment";
    }

    @GetMapping
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor-list";
    }

    @GetMapping("/viewBookings")
    public String viewBookings(@ModelAttribute("doctorId") Long doctorId, Model model) {
        List<PatientBooking> patientBookings = patientBookingService.getBookingsByDoctorId(doctorId);
        model.addAttribute("patientBookings", patientBookings);
        return "doctor/viewBookings";
    }
}
