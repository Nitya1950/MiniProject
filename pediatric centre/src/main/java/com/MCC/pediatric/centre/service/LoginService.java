package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.PatientEntity;
import com.MCC.pediatric.centre.repository.PatientRepository;
import com.MCC.pediatric.centre.web.model.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private PatientRepository pr;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean validateLogin(LoginForm loginForm){
        Optional<PatientEntity> optionalEntity=pr.findByEmail(loginForm.getEmail());
        if(!optionalEntity.isPresent()){
            logger.warn("Email not found ");
            return false;

        }

        PatientEntity patientEntity=optionalEntity.get();
        return passwordEncoder.matches(loginForm.getPassword(),patientEntity.getPwd());
    }
}
