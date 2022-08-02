package com.saint.claire.saint.claire.service;


import com.saint.claire.saint.claire.dto.AppointmentDTO;
import com.saint.claire.saint.claire.dto.AreaDTO;
import com.saint.claire.saint.claire.model.Appointment;
import com.saint.claire.saint.claire.model.Area;
import com.saint.claire.saint.claire.repository.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
public class
AreaService implements AreaGateway {

    private final static Logger log = LoggerFactory.getLogger(AreaService.class);
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
                .appointmentList(convertAppointListToDTO(area)).build();
    }

    private List<AppointmentDTO> getAppointmentDTOList(Area area) {
        return area.getAppointmentList()
                .stream()
                .map(this::convertAppointmentToDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO convertAppointmentToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .patientName(appointment.getPatientName())
                .build();
    }

    @Override
    public AreaDTO saveArea(AreaDTO areaDTO) {
        return convertAreaToDTO(areaRepository.save(buildArea(areaDTO)));
    }

    private Area buildArea(AreaDTO areaDTO) {
        return Area.builder()
                .id(areaDTO.getId())
                .name(areaDTO.getName())
                .doctor(areaDTO.getDoctor())
                .appointmentList(convertAppointListDTOToEntity(areaDTO))
                .build();
    }

    private List<Appointment> convertAppointListDTOToEntity(AreaDTO areaDTO) {
        return areaDTO.getAppointmentList()
                .stream()
                .map(appointmentDTO ->
                     Appointment
                            .builder()
                            .id(appointmentDTO.getId())
                            .date(appointmentDTO.getDate())
                            .patientName(appointmentDTO.getPatientName())
                             .areaId(appointmentDTO.getAreaId())
                            .build()).collect(Collectors.toList());
    }

    private List<AppointmentDTO> convertAppointListToDTO(Area area) {
        return area.getAppointmentList()
                .stream()
                .map(appointment ->
                     AppointmentDTO
                            .builder()
                            .id(appointment.getId())
                            .date(appointment.getDate())
                            .patientName(appointment.getPatientName())
                             .areaId(appointment.getAreaId())
                            .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteArea(Long id) {
       Area area = areaRepository.getReferenceById(id);
       if(isNull(area.getId())){
           log.info("The area's id doesn't exist");
           throw new RuntimeException("The id doesn't exist");
       }
       areaRepository.deleteById(id);

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
