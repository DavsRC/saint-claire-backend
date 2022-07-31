package com.saint.claire.saint.claire.service;

import com.saint.claire.saint.claire.dto.AreaDTO;

import java.util.List;

public interface AreaGateway {

    List<AreaDTO> getAreas();
    AreaDTO saveArea(AreaDTO areaDTO);
    void deleteArea(Long id);
    AreaDTO updateArea(AreaDTO areaDTO);
    AreaDTO getAreaById(Long id);
}
