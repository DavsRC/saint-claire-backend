package com.saint.claire.saint.claire.service;


import com.saint.claire.saint.claire.dto.AppointmentDTO;
import com.saint.claire.saint.claire.dto.AreaDTO;
import com.saint.claire.saint.claire.model.Appointment;
import com.saint.claire.saint.claire.model.Area;
import com.saint.claire.saint.claire.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Area area = buildArea(areaDTO);
        return convertAreaToDTO(areaRepository.save(area));
    }

    private Area buildArea(AreaDTO areaDTO) {
        return Area.builder()
                .id(areaDTO.getId())
                .name(areaDTO.getName())
                .doctor(areaDTO.getDoctor())
                .appointmentList(convertAppointListDTOToEntity(areaDTO))
                .build();
    }

    private void buildAreaDTO(Area area) {
        AreaDTO.builder()
                .id(area.getId())
                .name(area.getName())
                .doctor(area.getDoctor())
                .appointmentDTOList(convertAppointListToDTO(area))
                .build();
    }

    private List<Appointment> convertAppointListDTOToEntity(AreaDTO areaDTO) {
        return areaDTO.getAppointmentDTOList()
                .stream()
                .map(appointmentDTO -> {
                    return Appointment
                            .builder()
                            .id(appointmentDTO.getId())
                            .date(appointmentDTO.getDate())
                            .patientName(appointmentDTO.getPatientName())
                            .build();
                }).collect(Collectors.toList());
    }

    private List<AppointmentDTO> convertAppointListToDTO(Area area) {
        return area.getAppointmentList()
                .stream()
                .map(appointment -> {
                    return AppointmentDTO
                            .builder()
                            .id(appointment.getId())
                            .date(appointment.getDate())
                            .patientName(appointment.getPatientName())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteArea(Long id) {
        Optional<Area> area =  areaRepository.findById(id);
        if(area.isPresent()){
            areaRepository.deleteById(id);
        }
        throw new RuntimeException("The id doesn't exist");
    }

    @Override
    public AreaDTO updateArea(AreaDTO areaDTO) {
        Area area = buildArea(areaDTO);
        return convertAreaToDTO(areaRepository.save(area));
    }

    @Override
    public AreaDTO getAreaById(Long id) {
        Optional<Area> area =  areaRepository.findById(id);
        if(area.isPresent()){
           return convertAreaToDTO(area.get());
        }
        throw new RuntimeException("The id doesn't exist");
    }
}
