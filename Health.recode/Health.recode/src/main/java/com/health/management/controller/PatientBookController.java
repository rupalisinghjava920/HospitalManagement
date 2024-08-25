package com.health.management.controller;

import com.health.management.entity.Doctor;
import com.health.management.entity.MedicalAdvice;
import com.health.management.entity.PatientBooking;
import com.health.management.entity.User;
import com.health.management.helper.Message;
import com.health.management.repository.DoctorRepository;
import com.health.management.repository.MedicalAdviceRepository;
import com.health.management.repository.PatientBookRepository;
import com.health.management.sevice.DoctorService;
import com.health.management.sevice.MedicalAdviceService;
import com.health.management.sevice.PatientBookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class PatientBookController {

    @Autowired
    private MedicalAdviceRepository medicalAdviceRepository;
    @Autowired
    private MedicalAdviceService medicalAdviceService;
    @Autowired
    private PatientBookRepository patientBookRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientBookingService patientBookingService;
    @GetMapping("/patient/appointment")
    public String patientAppointment(){

        return "/patient/appointment";
    }

    @GetMapping("/patient/bookAppointment_form")
    public String createBookingForm(Model m){
        m.addAttribute("patient",new PatientBooking());
        return "patient/bookAppointment_form";
    }
    @PostMapping("/do_bookAppointment")
    public String bookAppointment(@ModelAttribute PatientBooking patientBooking, HttpSession session,@RequestParam Long doctorId){
        try {
            PatientBooking patient1 = patientBookingService.bookingAppointment(patientBooking,doctorId);
            session.setAttribute("message", new Message("Booking Successfully  !!", "alert-success"));
        }catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Booking Not Successfully ", "alert-danger"));
        }
        return "patient/bookAppointment_form";
    }


    @GetMapping("/patient/patientBooking-list")
    public String getAllPatientBookings(Model model) {
        System.out.println("called petaint list test");
        List<PatientBooking> patientBookings = patientBookingService.getAllPatientBookings();
        model.addAttribute("patientBookings",patientBookings);
        System.out.println("called petaint list"+patientBookings);
        return "patient/patientBooking-list";
    }

    @GetMapping("/patient/view_advice")
    public String viewAdvice(Model model) {
        List<MedicalAdvice>  medicalAdvices=medicalAdviceService.getAllMadicalAdvises();
        model.addAttribute("medicalAdvises",medicalAdvices);
        return "patient/view_advice";
    }


}

