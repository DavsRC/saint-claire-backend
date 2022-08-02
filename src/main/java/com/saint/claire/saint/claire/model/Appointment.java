package com.saint.claire.saint.claire.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Appointment")
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment", nullable = false)
    private Long id;
    @Column(name= "date")
    private Date date;
    @Column(name= "patientName")
    private String patientName;
    @JoinColumn(name = "areaId")
    @Column(name= "areaId")
    private Long areaId;

}
