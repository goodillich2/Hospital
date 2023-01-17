package com.example.springmarket.repository;

import com.example.springmarket.model.TimeOfAppointment;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeOfAppointmentRepository extends JpaRepository<TimeOfAppointment, Integer> {

}
