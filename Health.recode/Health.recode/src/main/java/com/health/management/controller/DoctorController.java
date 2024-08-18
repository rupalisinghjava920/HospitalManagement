package com.health.management.controller;

import com.health.management.entity.Doctor;
import com.health.management.entity.PatientBooking;
import com.health.management.helper.Message;
import com.health.management.sevice.DoctorService;
import com.health.management.sevice.PatientBookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor",new Doctor());
        return "doctor/patientAppointment";
    }

    @PostMapping("/do_viewDoctor")
    public String saveDoctor(@ModelAttribute Doctor doctor, HttpSession session){
        try{
        doctorService.saveDoctor(doctor);
        session.setAttribute("message",new Message(" Successfully Submit!!","alert-success"));
        }catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message(" Not Successfully ", "alert-danger"));
        }
        return "/doctor/patientAppointment";
    }

    @GetMapping("/doctor_list")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/doctor_list";
    }


    @GetMapping("/{doctorId}/viewBookings")
    public String viewBookings(@PathVariable("doctorId") Long doctorId, Model model) {
        System.out.println("Doctor ID: " + doctorId);
        List<PatientBooking> patientBookings = patientBookingService.getBookingsByDoctorId(doctorId);
        model.addAttribute("patientBookings", patientBookings);
        return "doctor/viewBookings";
    }

//    @GetMapping("/{doctorId}")
//    public String getDoctorById(@PathVariable("doctorId") Long doctorId, Model model) {
//        Doctor doctor = doctorService.getDoctorById(doctorId);
//        if (doctor == null) {
//            // Handle the case where doctor is not found (optional)
//            model.addAttribute("message", "Doctor not found");
//            return "error";
//        }
//        model.addAttribute("doctor", doctor);
//        return "doctor/doctor_list";
//    }
}
