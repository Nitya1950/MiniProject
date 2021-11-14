package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.AppointmentService;
import com.MCC.pediatric.centre.web.model.AppointmentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private static Logger logger = LoggerFactory.getLogger(AppointmentController.class);
@Autowired
private AppointmentService as;
    @PostMapping
    public ResponseEntity<?> createAppointment(@ModelAttribute AppointmentForm form){
        logger.info("Received new appointment.");
        as.save(form);
        logger.info("Appointment saved.");
        return ResponseEntity.ok().body("The Appointment has been booked successfully. Please check back later for our Doctors to confirm it." ) ;
    }

    @GetMapping("/{patientId}/create")
    public String newAppointment(@PathVariable String patientId, Model model) {
        logger.info("Received create appointment");
        model.addAttribute("patientId", patientId);
        return "Appointment";
    }

    @PostMapping("/{appointmentId}/confirm")
    public ResponseEntity<?> confirmAppointment(@PathVariable String appointmentId ){
        logger.info("Received confirmation request"+appointmentId);
        as.confirmAppointment(appointmentId);
        return ResponseEntity.ok().body("Appointment confirmed");
    }

    @PostMapping("/{appointmentId}/decline")
    public ResponseEntity<?> declineAppointment(@PathVariable String appointmentId ){
        logger.info("Received decline request"+appointmentId);
        as.declineAppointment(appointmentId);
        return ResponseEntity.ok().body("Appointment declined");
    }
}

