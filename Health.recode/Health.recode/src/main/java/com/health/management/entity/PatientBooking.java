package com.health.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="patientbook_tbl")
public class PatientBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String gender;
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofbirth;

    @Email
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;


    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public PatientBooking() {}
}
