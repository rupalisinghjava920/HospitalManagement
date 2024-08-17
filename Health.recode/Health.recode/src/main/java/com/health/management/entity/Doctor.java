package com.health.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="doctor_tbl")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String specialization;

    @Email
    private String email;
    private String phone;
    private String address;
    private String city;
    private String zip;
    private String gender;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<PatientBooking> patientBookings;

    public Doctor() {}
}
