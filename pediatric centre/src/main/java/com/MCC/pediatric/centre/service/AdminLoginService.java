package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.AdminEntity;
import com.MCC.pediatric.centre.repository.AdminRepository;
import com.MCC.pediatric.centre.web.model.AdminLoginForm;
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
}
