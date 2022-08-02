package com.saint.claire.saint.claire.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.List;

@Data
@Builder
@Log
public class AreaDTO {

    private Long id;
    private String name;
    private String doctor;
    private List<AppointmentDTO> appointmentList;


}
