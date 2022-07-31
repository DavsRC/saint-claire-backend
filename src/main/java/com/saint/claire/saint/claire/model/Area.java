package com.saint.claire.saint.claire.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Area")
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area", nullable = false)
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name= "doctor")
    private String doctor;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

}