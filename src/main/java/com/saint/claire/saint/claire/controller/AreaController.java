package com.saint.claire.saint.claire.controller;

import com.saint.claire.saint.claire.dto.AreaDTO;
import com.saint.claire.saint.claire.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "save")
    public AreaDTO saveArea(@RequestBody AreaDTO areaDTO){
        return areaService.saveArea(areaDTO);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteArea(@PathVariable("id")Long id){
        areaService.deleteArea(id);
    }
}
