package com.health.management.sevice;

import com.health.management.entity.MedicalAdvice;
import com.health.management.entity.PatientBooking;
import com.health.management.repository.MedicalAdviceRepository;
import com.health.management.repository.PatientBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalAdviceService {

    @Autowired
    private MedicalAdviceRepository medicalAdviceRepository;


    public void createMedicalAdvice(MedicalAdvice medicalAdvice) {
        medicalAdviceRepository.save(medicalAdvice);
    }

    public List<MedicalAdvice> getAllMadicalAdvises(){
        return medicalAdviceRepository.findAll();
    }


}


