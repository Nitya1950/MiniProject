package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.PatientEntity;
import com.MCC.pediatric.centre.repository.PatientRepository;
import com.MCC.pediatric.centre.web.model.Patient;
import com.MCC.pediatric.centre.web.model.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PatientService {
    private static Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository pr;
    @Transactional
    public Patient save(RegistrationForm registrationForm){
        PatientEntity entity= new PatientEntity();
        Random rand = new Random();
        int min=10000, max=99999;
        int num = ThreadLocalRandom.current().nextInt(min, max + 1);
        entity.setId("P"+String.valueOf(num));
        entity.setFname(registrationForm.getFname());
        entity.setLname(registrationForm.getLname());
        entity.setAddress(registrationForm.getAddress());
        entity.setDob(registrationForm.getDob());
        entity.setEmail(registrationForm.getEmail());
        entity.setGender(registrationForm.getGender());
        entity.setPhno(registrationForm.getPhno());
        entity.setPincode(registrationForm.getPincode());
        //mapped all the attributes doneee

        logger.info("Saving patient.");
        pr.save(entity);
        logger.info("Saved patient.");
        return convert(entity);
    }
    private Patient convert(PatientEntity entity){
        Patient patient= new Patient();
        patient.setId(entity.getId());
        patient.setFname(entity.getFname());
        patient.setLname(entity.getLname());
        patient.setGender(entity.getGender());
        patient.setDob(entity.getDob());
        patient.setAddress(entity.getAddress());
        patient.setEmail(entity.getEmail());
        patient.setPhno(entity.getPhno());
        patient.setPincode(entity.getPincode());
        //writTEN
        return patient;
    }
    //public void getPatient(String email){
       // pr.findbyEmail(email);

    }

