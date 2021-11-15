package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.AppointmentService;
import com.MCC.pediatric.centre.service.PatientService;
import com.MCC.pediatric.centre.web.model.Appointment;
import com.MCC.pediatric.centre.web.model.Patient;
import com.MCC.pediatric.centre.web.model.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  //required for autowiring
@RequestMapping("/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService ps;
    @Autowired
    private AppointmentService as;

    @PostMapping
    public String createRegistration(@ModelAttribute RegistrationForm form, Model model){
        logger.info("Received new registration.");
        Patient p = ps.save(form);

        model.addAttribute("patient",p);
        List<Appointment> appointments = as.listAppointments(p);
        model.addAttribute("appointments", appointments);
        logger.info("Registration saved.");
        return "userhome";
    }
    @GetMapping("/{patientid}")
    public String displayuser(@PathVariable String patientid, Model model){
        Patient p = ps.findById(patientid);
        model.addAttribute("patient",p);
        List<Appointment> appointments = as.listAppointments(p);
        model.addAttribute("appointments", appointments);
        logger.info("Registration saved.");
        return "userhome";
    }

}

