package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.PatientService;
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

@Controller  //required for autowiring
@RequestMapping("/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService ps;

    @PostMapping
    public String createRegistration(@ModelAttribute RegistrationForm form, Model model){
        logger.info("Received new registration.");
        ps.save(form);
        model.addAttribute("name",form.getFname());
        logger.info("Registration saved.");
        return "userhome";
    }
}

