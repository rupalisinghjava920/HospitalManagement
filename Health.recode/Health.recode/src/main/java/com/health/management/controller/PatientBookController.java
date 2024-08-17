package com.health.management.controller;

import com.health.management.entity.PatientBooking;
import com.health.management.entity.User;
import com.health.management.helper.Message;
import com.health.management.sevice.PatientBookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientBookController {

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
    public String bookAppointment(@ModelAttribute PatientBooking patientBooking, HttpSession session){
         try {
             PatientBooking patient1 = patientBookingService.bookingAppointment(patientBooking);
             session.setAttribute("message", new Message("Booking Successfully  !!", "alert-success"));
         }catch (Exception e) {
             e.printStackTrace();
             session.setAttribute("message", new Message("Booking Not Successfully ", "alert-danger"));
         }
        return "patient/bookAppointment_form";
    }

    @GetMapping("/patient/booking")
    public String getAllPatientBookings(Model model) {
        List<PatientBooking> patientBookings = patientBookingService.getAllPatientBookings();
        model.addAttribute("patientBookings",patientBookings);
        return "patient/patientBooking-list";
    }


}
