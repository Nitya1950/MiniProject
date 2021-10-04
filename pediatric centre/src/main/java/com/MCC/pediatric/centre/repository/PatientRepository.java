package com.MCC.pediatric.centre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, String> {

     public Optional<PatientEntity> findByEmail(String email);
     
}

