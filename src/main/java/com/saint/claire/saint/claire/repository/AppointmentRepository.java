package com.saint.claire.saint.claire.repository;

import com.saint.claire.saint.claire.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
