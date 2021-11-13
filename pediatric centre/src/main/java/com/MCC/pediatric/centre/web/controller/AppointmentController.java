package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.AppointmentService;
import com.MCC.pediatric.centre.web.model.AppointmentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @PostMapping("/{appointmentId}")
    public ResponseEntity<?> confirmAppointment(@PathVariable String appointmentId ){
        logger.info("Received confirmation request"+appointmentId);
        as.confirmAppointment(appointmentId);
        return ResponseEntity.ok().body("Appointment confirmed");
    }
}

