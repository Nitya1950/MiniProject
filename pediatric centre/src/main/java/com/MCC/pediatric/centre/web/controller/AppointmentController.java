package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.repository.PatientRepository;
import com.MCC.pediatric.centre.service.AdminLoginService;
import com.MCC.pediatric.centre.service.AppointmentService;
import com.MCC.pediatric.centre.service.PatientService;
import com.MCC.pediatric.centre.web.model.Admin;
import com.MCC.pediatric.centre.web.model.Appointment;
import com.MCC.pediatric.centre.web.model.AppointmentForm;
import com.MCC.pediatric.centre.web.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    private static Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    @Autowired
    private AppointmentService as;
    @Autowired
    private PatientService ps;
    @Autowired
    private AdminLoginService als;

    @PostMapping
    public String createAppointment(@ModelAttribute AppointmentForm form, Model model){
        logger.info("Received new appointment.");
        if (form.getAppointmentDate().isBefore(LocalDate.now())){
            model.addAttribute("message","Appointment cannot be booked in the past");
            model.addAttribute("patientId", form.getPatientId());
            return "Appointment";
        }
        as.save(form);
        Patient p = ps.findById(form.getPatientId());
        model.addAttribute("patient",p);
        List<Appointment> appointments = as.listAppointments(p);
        model.addAttribute("appointments", appointments);
        logger.info("Appointment saved.");
        return "userhome";
    }

    @GetMapping("/{patientId}/create")
    public String newAppointment(@PathVariable String patientId, Model model) {
        logger.info("Received create appointment");
        model.addAttribute("patientId", patientId);
        return "Appointment";
    }

    @PostMapping("/{appointmentId}/confirm")
    public String confirmAppointment(@PathVariable String appointmentId, Model model){
        logger.info("Received confirmation request"+appointmentId);
        Appointment appointment = as.confirmAppointment(appointmentId);
        Admin admin = als.findByName(appointment.getDoctorname());
        model.addAttribute("admin",admin);
        List<Appointment> list = as.listAppointments(admin);
        model.addAttribute("appointments",list);
        return "Doctorhome";
    }

    @PostMapping("/{appointmentId}/decline")
    public String declineAppointment(@PathVariable String appointmentId, Model model){
        logger.info("Received decline request"+appointmentId);
        Appointment appointment = as.declineAppointment(appointmentId);
        Admin admin = als.findByName(appointment.getDoctorname());
        model.addAttribute("admin",admin);
        List<Appointment> list = as.listAppointments(admin);
        model.addAttribute("appointments",list);
        return "Doctorhome";
    }
}

