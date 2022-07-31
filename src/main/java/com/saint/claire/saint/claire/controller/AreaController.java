package com.saint.claire.saint.claire.controller;

import com.saint.claire.saint.claire.dto.AreaDTO;
import com.saint.claire.saint.claire.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("Area/")
@CrossOrigin("*")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("areas")
    public List<AreaDTO> getAreas(){
        return areaService.getAreas();
    }

    @GetMapping(value = "/{id}")
    public AreaDTO getListById(@PathVariable() Long id){
        return areaService.getAreaById(id);
    }

    @PostMapping(value = "save")
    public AreaDTO saveArea(@RequestBody AreaDTO areaDTO){
        return areaService.saveArea(areaDTO);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteArea(@PathVariable("id")Long id){
        areaService.deleteArea(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<AreaDTO> updateArea(@RequestBody AreaDTO areaDTO){
        if(!isNull(areaDTO.getId())){
            return new ResponseEntity<>(areaService.updateArea(areaDTO), HttpStatus.OK);
        }
        throw new RuntimeException("The id doesn't exist");
    }

}
