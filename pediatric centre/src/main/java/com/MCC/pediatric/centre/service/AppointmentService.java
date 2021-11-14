package com.MCC.pediatric.centre.service;

import com.MCC.pediatric.centre.repository.AppointmentEntity;
import com.MCC.pediatric.centre.repository.AppointmentRepository;
import com.MCC.pediatric.centre.repository.PatientEntity;
import com.MCC.pediatric.centre.repository.PatientRepository;
import com.MCC.pediatric.centre.web.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.MCC.pediatric.centre.web.model.AppointmentStatus.*;

@Service
public class AppointmentService {
    private static Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository ar;
    @Autowired
    private PatientRepository pr;

    @Transactional
    public Appointment save(AppointmentForm appointmentForm){
        AppointmentEntity entity = new AppointmentEntity();
        Random rand = new Random();
        int num = rand.nextInt(10000)+10000;

        PatientEntity patientEntity = pr.findById(appointmentForm.getPatientId()).get();
        entity.setId("A"+String.valueOf(num));
        entity.setPatient(patientEntity);
        entity.setFullName(appointmentForm.getFullName());
        entity.setAppointmentDate(appointmentForm.getAppointmentDate());
        entity.setDoctorname(appointmentForm.getDoctorname());
        entity.setStatus(PENDING);
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

        List<Appointment> list = new ArrayList<>();
        for ( AppointmentEntity ap : all){
            list.add(convert(ap));
        }
        return list;
    }

    @Transactional
    public List<Appointment> listAppointments(Patient p) {
        List<AppointmentEntity> list = ar.findAllByPatientId(p.getId());
        List<Appointment> returnList = new ArrayList<>();
        for ( AppointmentEntity ap : list ) {
            returnList.add(convert(ap));
        }
        return returnList;
    }

    private Appointment convert(AppointmentEntity entity) {
        Appointment appointment= new Appointment();
        appointment.setId(entity.getId());
        appointment.setFullName(entity.getFullName());
        appointment.setAppointmentDate(entity.getAppointmentDate());
        appointment.setDoctorname(entity.getDoctorname());
        appointment.setStatus(entity.getStatus());
        //writTEN
        return appointment;
    }

    @Transactional
    public void confirmAppointment(String appointmentId) {
        changeAppointmentStatus(appointmentId, CONFIRMED);
    }

    @Transactional
    public void declineAppointment(String appointmentId) {
        changeAppointmentStatus(appointmentId, DECLINED);
    }

    private void changeAppointmentStatus(String appointmentId, AppointmentStatus newStatus) {
        Optional<AppointmentEntity> optional= ar.findById(appointmentId);
        if(!optional.isPresent()){
            throw new IllegalArgumentException("No such appointment");
        }else
        {
            AppointmentEntity entity=optional.get();
            entity.setStatus(newStatus);
            ar.save(entity);
        }
    }
}


