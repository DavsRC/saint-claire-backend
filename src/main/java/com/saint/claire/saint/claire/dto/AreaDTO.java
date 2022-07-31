package com.saint.claire.saint.claire.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AreaDTO {

    private Long id;
    private String name;
    private String doctor;
    private List<AppointmentDTO> appointmentDTOList;


}
