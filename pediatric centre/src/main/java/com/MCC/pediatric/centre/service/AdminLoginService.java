package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.AdminEntity;
import com.MCC.pediatric.centre.repository.AdminRepository;
import com.MCC.pediatric.centre.repository.PatientEntity;
import com.MCC.pediatric.centre.web.model.Admin;
import com.MCC.pediatric.centre.web.model.AdminLoginForm;
import com.MCC.pediatric.centre.web.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminLoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private AdminRepository ar;

    @Transactional
    public boolean validateLogin(AdminLoginForm adminloginForm){
        Optional<AdminEntity> optionalEntity=ar.findByEmail(adminloginForm.getEmail());
        if(!optionalEntity.isPresent()){
            logger.warn("Email not found ");
            return false;

        }

        AdminEntity adminEntity=optionalEntity.get();
        return adminEntity.getPassword().equals(adminloginForm.getPassword());
    }
    private Admin convert(AdminEntity entity){
        Admin admin=new Admin();
        admin.setId(entity.getId());
        admin.setName(entity.getName());
        admin.setEmail(entity.getEmail());
        admin.setPassword(entity.getPassword());
        admin.setDoctor(entity.isDoctor());
        return admin;
    }

    @Transactional
    public Admin findByEmail(String email){
        return ar.findByEmail(email).map(this::convert).orElse(null);
    }
}
