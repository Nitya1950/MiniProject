package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.LoginService;
import com.MCC.pediatric.centre.service.PatientService;
import com.MCC.pediatric.centre.web.model.LoginForm;
import com.MCC.pediatric.centre.web.model.Patient;
import com.MCC.pediatric.centre.web.model.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService ls;
    @Autowired
    private PatientService ps;

    @PostMapping
    public String login(@ModelAttribute LoginForm loginForm, Model model) {
        logger.info("Received Login");
        if(ls.validateLogin(loginForm)) {
            logger.info("Login success.");
            Patient p= ps.findByEmail(loginForm.getEmail());
            model.addAttribute("name",p.getFname());
            return "userhome";
        }
        else{
            logger.warn("Invalid login");
            model.addAttribute("message","Invalid username or password");
            return "login";
        }
    }

}

