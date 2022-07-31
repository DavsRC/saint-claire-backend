package com.saint.claire.saint.claire.service;


import com.saint.claire.saint.claire.dto.AppointmentDTO;
import com.saint.claire.saint.claire.dto.AreaDTO;
import com.saint.claire.saint.claire.model.Appointment;
import com.saint.claire.saint.claire.model.Area;
import com.saint.claire.saint.claire.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class
AreaService implements AreaGateway {

    @Autowired
    private AreaRepository areaRepository;


    @Override
    public List<AreaDTO> getAreas() {
        return areaRepository.findAll()
                .stream()
                .map(this::convertAreaToDTO)
                .collect(Collectors.toList());
    }

    private AreaDTO convertAreaToDTO(Area area) {
        return AreaDTO.builder()
                .id(area.getId())
                .name(area.getName())
                .doctor(area.getDoctor())
                .appointmentDTOList(getAppointmentDTOList(area)).build();
    }

    private List<AppointmentDTO> getAppointmentDTOList(Area area) {
        return area.getAppointmentList()
                .stream()
                .map(this::convertAppointmentToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO convertAppointmentToDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .patientName(appointment.getPatientName())
                .build();
        return appointmentDTO;
    }

    @Override
    public AreaDTO saveArea(AreaDTO areaDTO) {
        Area area = Area.builder()
                .id(areaDTO.getId())
                .name(areaDTO.getName())
                .doctor(areaDTO.getDoctor())
                .appointmentList(convertAppointListDTOToEntity(areaDTO))
                .build();
        return convertAreaToDTO(areaRepository.save(area));
    }

    private List<Appointment> convertAppointListDTOToEntity(AreaDTO areaDTO) {
        return areaDTO.getAppointmentDTOList()
                .stream()
                .map(appointmentDTO -> {
                    Appointment appointment = Appointment
                            .builder()
                            .id(appointmentDTO.getId())
                            .date(appointmentDTO.getDate())
                            .patientName(appointmentDTO.getPatientName())
                            .build();
                    return appointment;
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteArea(Long id) {

    }

    @Override
    public AreaDTO updateArea(AreaDTO areaDTO) {
        return null;
    }

    @Override
    public AreaDTO getAreaById(Long id) {
      return null;
    }
}
