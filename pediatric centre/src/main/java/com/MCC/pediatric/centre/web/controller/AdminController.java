package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.AdminLoginService;
import com.MCC.pediatric.centre.service.AppointmentService;
import com.MCC.pediatric.centre.service.BroadcastMessageService;
import com.MCC.pediatric.centre.web.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AdminLoginService as;
    @Autowired
    private AppointmentService aps;
    @Autowired
    private BroadcastMessageService bms;

    @PostMapping("/login")
    public String Adminlogin(@ModelAttribute AdminLoginForm adminloginForm, Model model) {
        logger.info("Received Admin Login");
        if(as.validateLogin(adminloginForm)) {
            logger.info("Login success.");
            Admin a= as.findByEmail(adminloginForm.getEmail());
            model.addAttribute("admin",a);
            List<Appointment> list = aps.listAppointments(a);
            model.addAttribute("appointments",list);
            if(a.isDoctor())
                return "Doctorhome";
            else {
                String message = bms.getBroadcastMessage().orElse("");
                model.addAttribute("broadcastMessage", message);
                return "Adminhome";
            }
        }
        else{
            logger.warn("Invalid login");
            model.addAttribute("message","Invalid username or password");
            return "AdminLogin";
        }
    }

    @GetMapping("/{adminid}")
    public String displayuser(@PathVariable String adminid, Model model){
       Admin a = as.findById(adminid);
        model.addAttribute("admin",a);
        List<Appointment> appointments = aps.listAppointments(a);
        model.addAttribute("appointments", appointments);
        logger.info("Registration saved.");
        return "Doctorhome";
    }

    @PostMapping("/broadcast")
    public String broadcastMessage(@ModelAttribute BroadcastMessageForm form, Model model) {
        bms.setBroadcastMessage(form.getMessage());
        Admin a = as.findById(form.getAdminId());
        model.addAttribute("admin",a);
        List<Appointment> appointments = aps.listAppointments(a);
        model.addAttribute("appointments", appointments);
        model.addAttribute("broadcastMessage", form.getMessage());
        return "AdminHome";
    }
}
