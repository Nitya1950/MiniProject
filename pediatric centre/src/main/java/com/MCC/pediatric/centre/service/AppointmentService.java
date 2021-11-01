package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.AppointmentEntity;
import com.MCC.pediatric.centre.repository.AppointmentRepository;
import com.MCC.pediatric.centre.repository.PatientEntity;
import com.MCC.pediatric.centre.web.model.Admin;
import com.MCC.pediatric.centre.web.model.Appointment;
import com.MCC.pediatric.centre.web.model.AppointmentForm;
import com.MCC.pediatric.centre.web.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AppointmentService {
    private static Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository ar;
    @Transactional
    public Appointment save(AppointmentForm appointmentForm){
        AppointmentEntity entity = new AppointmentEntity();
        Random rand = new Random();
        int num = rand.nextInt(10000)+10000;
        entity.setId("A"+String.valueOf(num));
        entity.setFullName(appointmentForm.getFullName());
        entity.setMobileNumber(appointmentForm.getMobileNumber());
        entity.setEmailId(appointmentForm.getEmailId());
        entity.setAppointmentDate(appointmentForm.getAppointmentDate());
        entity.setArea(appointmentForm.getArea());
        entity.setCity(appointmentForm.getCity());
        entity.setState(appointmentForm.getState());
        entity.setPostalCode(appointmentForm.getPostalCode());
        entity.setDoctorname(appointmentForm.getDoctorname());
        //mapped all the attributes done

        logger.info("Saving appointment.");
        ar.save(entity);
        logger.info("Saved appointment.");
        return convert(entity);
    }
    @Transactional
    public List<Appointment> listAppointments(Admin a) {
        List<AppointmentEntity> all =
                a.isDoctor() ? ar.findAllByDoctorname(a.getName()) : ar.findAll() ;
         ar.findAll();
        List<Appointment> list = new ArrayList<>();
        for ( AppointmentEntity ap : all){
            list.add(convert(ap));
        }
        return list;
    }

    private Appointment convert(AppointmentEntity entity) {
        Appointment appointment= new Appointment();
        appointment.setId(entity.getId());
        appointment.setFullName(entity.getFullName());
        appointment.setMobileNumber(entity.getMobileNumber());
        appointment.setEmailId(entity.getEmailId());
        appointment.setAppointmentDate(entity.getAppointmentDate());
        appointment.setArea(entity.getArea());
        appointment.setCity(entity.getCity());
        appointment.setState(entity.getState());
        appointment.setPostalCode(entity.getPostalCode());
        appointment.setDoctorname(entity.getDoctorname());
        //writTEN
        return appointment;
    }


    }


