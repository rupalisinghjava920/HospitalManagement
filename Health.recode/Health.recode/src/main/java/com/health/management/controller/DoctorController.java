package com.health.management.controller;

import com.health.management.entity.Doctor;
import com.health.management.entity.MedicalAdvice;
import com.health.management.entity.PatientBooking;
import com.health.management.helper.Message;
import com.health.management.repository.DoctorRepository;
import com.health.management.repository.PatientBookRepository;
import com.health.management.sevice.DoctorService;
import com.health.management.sevice.MedicalAdviceService;
import com.health.management.sevice.PatientBookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private MedicalAdviceService medicalAdviceService;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientBookRepository patientBookRepository;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientBookingService patientBookingService;

    //doctor form get
    @GetMapping("/patientAppointment")
    public String createDoctorForm(Model model) {
        model.addAttribute("doctor",new Doctor());
        return "doctor/patientAppointment";
    }

    //doctor save form
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

    //doctor list
    @GetMapping("/doctor_list")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/doctor_list";
    }
    //patientBooking list
        @GetMapping("/patientBooking-list")
        public String getAllPatientBookings(Model model) {
            List<PatientBooking> patientBookings = patientBookingService.getAllPatientBookings();
            model.addAttribute("patientBookings",patientBookings);
            return "doctor/patientBooking-list";
        }

    //doctor accept
    @PostMapping("/patientBooking-list/accept/{id}")
    public String acceptBooking(@PathVariable Long id) {
        patientBookingService.updateBookingStatus(id, "Accepted");
        return "redirect:/doctor/patientBooking-list";
    }


    //doctor reject
    @PostMapping("/patientBooking-list/reject/{id}")
    public String rejectBooking(@PathVariable Long id) {
        patientBookingService.updateBookingStatus(id, "Rejected");
        return "redirect:/doctor/patientBooking-list";
    }

    //doctor advise get
    @GetMapping("/advise_form")
    public String showAdviceForm (){
        System.out.println("asdfghjn");
        return "doctor/advise_form";
    }

    //doctor advise save
    @PostMapping("/advise")
    public String saveAdvice(@ModelAttribute MedicalAdvice medicalAdvice,HttpSession session){
        try {
            medicalAdviceService.createMedicalAdvice(medicalAdvice);
            session.setAttribute("message",new Message("Doctor Advice Save Successfully","alert-success"));
        }catch (Exception e){
            session.setAttribute("message",new Message("Doctor Advice not Save","alert-danger"));
        }
            return "redirect:/doctor/advise_form";
    }


    //Specialization doctor
   @GetMapping("/doctor-patients")
    public String showPatientsBySpecialization(@RequestParam(value = "specialization", required = false) String specialization, Model model) {

       List<Doctor> doctors = patientBookingService.getDoctorByPatientSpecialization(specialization);
       model.addAttribute("doctors", doctors);
       System.out.println("show Patients By Specialization :"+specialization);
        return "doctor/doctor-patients";
    }
   //patientBooking medicalCondition filter
    @GetMapping("/patientBooking-list/medicalCondition")
    public String getPatientBookingsBySpecialization(@RequestParam("medicalCondition") String medicalCondition, Model model) {
        System.out.println("Medical Condition: " + medicalCondition);
        List<PatientBooking> patientBookings;
        if (medicalCondition == null || medicalCondition.isEmpty()) {
            patientBookings = patientBookingService.getAllPatientBookings();
        } else {
            patientBookings = patientBookingService.getPatientBookingsByMedicalCondition(medicalCondition);
        }
        model.addAttribute("patientBookings", patientBookings);
        return "doctor/patientBooking-list";
    }


}
