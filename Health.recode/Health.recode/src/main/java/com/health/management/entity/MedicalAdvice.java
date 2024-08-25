package com.health.management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MedicalAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adivseMedication;
    private String diagnostics;


    public MedicalAdvice() {}

}
