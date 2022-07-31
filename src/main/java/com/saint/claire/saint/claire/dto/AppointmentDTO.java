package com.saint.claire.saint.claire.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AppointmentDTO {

    private Long id;
    private Date date;
    private String patientName;
    private Long appointmentId;
}
