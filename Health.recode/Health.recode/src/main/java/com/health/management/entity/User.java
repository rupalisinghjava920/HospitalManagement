package com.health.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="user_tbl")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name field is required !!")
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    @Column(length = 10)
    private long  phone;
    private String address;
    private String city;

    private String role;

//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    private List<PatientBooking> patientBookings;

//    @OneToMany
//    @JoinColumn(name = "doctor_id")
//    private Doctor doctor;
}
