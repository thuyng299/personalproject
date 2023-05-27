package com.nonit.personalproject.service;

import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaDTO;

import java.util.List;

public interface WarehouseAreaService {
    List<WarehouseAreaDTO> getAllWarehouseArea();
    WarehouseAreaDTO findWarehouseAreaById (Long areaId);
    WarehouseAreaDTO createWarehouseArea (WarehouseAreaCreateDTO warehouseAreaCreateDTO);
    void deleteWarehouseArea (Long areaId);
}
