package com.saint.claire.saint.claire.service;

import com.saint.claire.saint.claire.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentGateway {

    List<AppointmentDTO> getAppointments();
    AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO getAppointmentById(Long id);
}
