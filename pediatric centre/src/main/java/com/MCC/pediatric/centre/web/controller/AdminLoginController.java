package com.MCC.pediatric.centre.web.controller;

import com.MCC.pediatric.centre.service.AdminLoginService;
import com.MCC.pediatric.centre.service.AdminService;
import com.MCC.pediatric.centre.web.model.LoginForm;
import com.MCC.pediatric.centre.web.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/login")
public class AdminLoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AdminLoginService ls;
    @Autowired
    private AdminService as;

    @PostMapping
    public String Adminlogin(@ModelAttribute AdminLoginForm adminloginForm, Model model) {
        logger.info("Received Admin Login");
        if(ls.validateLogin(adminloginForm)) {
            logger.info("Login success.");
            Admin a= as.findByEmail(adminloginForm.getEmail());
            return "adminhome";
        }
        else{
            logger.warn("Invalid login");
            model.addAttribute("message","Invalid username or password");
            return "adminlogin";
        }
    }
}
