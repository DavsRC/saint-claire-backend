package com.saint.claire.saint.claire.controller;

import com.saint.claire.saint.claire.dto.AppointmentDTO;
import com.saint.claire.saint.claire.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("Appointment/")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("appointments")
    public List<AppointmentDTO> getAppointments(){
        return appointmentService.getAppointments();
    }

    @GetMapping(value = "/{id}")
    public AppointmentDTO getListById(@PathVariable() Long id){
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping(value = "save")
    public AppointmentDTO saveAppointment(@RequestBody AppointmentDTO appointmentDTO){
        return appointmentService.saveAppointment(appointmentDTO);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteAppointment(@PathVariable("id")Long id){
        appointmentService.deleteAppointment(id);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO){
        if(!isNull(appointmentDTO.getId())){
            return new ResponseEntity<>(appointmentService.updateAppointment(appointmentDTO), HttpStatus.OK);
        }
        throw new RuntimeException("The id doesn't exist");
    }
}
