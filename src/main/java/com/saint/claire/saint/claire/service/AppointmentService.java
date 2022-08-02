package com.saint.claire.saint.claire.service;

import com.saint.claire.saint.claire.dto.AppointmentDTO;
import com.saint.claire.saint.claire.model.Appointment;
import com.saint.claire.saint.claire.model.Area;
import com.saint.claire.saint.claire.repository.AppointmentRepository;
import com.saint.claire.saint.claire.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements AppointmentGateway{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<AppointmentDTO> getAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::convertAppointmentToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO convertAppointmentToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .patientName(appointment.getPatientName())
                .areaId(appointment.getAreaId())
                .build();
    }

    @Override
    public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO) {
         Appointment appointment = buildAppointment(appointmentDTO);
         return convertAppointmentToDTO(appointmentRepository.save(appointment));
    }

    private Appointment buildAppointment(AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .id(appointmentDTO.getId())
                .date(appointmentDTO.getDate())
                .patientName(appointmentDTO.getPatientName())
                .areaId(appointmentDTO.getAreaId())
                .build();
    }

    @Override
    public void deleteAppointment(Long id) {
        Optional<Appointment> appointment =  appointmentRepository.findById(id);
        if(appointment.isPresent()){
            AppointmentDTO appointmentDTO = convertAppointmentToDTO(appointment.get());
            appointmentRepository.deleteById(appointmentDTO.getId());
        }
        throw new RuntimeException("The id doesn't exist");
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = buildAppointment(appointmentDTO);
        return convertAppointmentToDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Optional<Appointment> appointment =  appointmentRepository.findById(id);
        if(appointment.isPresent()){
            return convertAppointmentToDTO(appointment.get());
        }
        throw new RuntimeException("The id doesn't exist");
    }
}
