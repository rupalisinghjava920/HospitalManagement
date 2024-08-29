package com.health.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString(exclude = "doctor")
@Entity
@Table(name="patientbook")
public class PatientBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String gender;
    private String phone;

    private String status;
    private String medicalCondition;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofbirth;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    //private String doctorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

//    @ManyToOne
//    @JoinColumn(name = "patientBooking_id",nullable = false)
//    private User user;
    public PatientBooking() {
    }

}
