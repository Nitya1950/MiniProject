package com.MCC.pediatric.centre.repository;

import com.MCC.pediatric.centre.web.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, String> {
    List<AppointmentEntity> findAllByDoctorname(String doctorname);
}

