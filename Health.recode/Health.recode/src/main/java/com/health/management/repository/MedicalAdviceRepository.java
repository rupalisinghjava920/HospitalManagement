package com.health.management.repository;

import com.health.management.entity.MedicalAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalAdviceRepository extends JpaRepository<MedicalAdvice,Long> {


    }


