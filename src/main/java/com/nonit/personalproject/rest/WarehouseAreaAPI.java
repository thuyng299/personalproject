package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/warehouseareas")
public interface WarehouseAreaAPI {
    @GetMapping
    ResponseEntity<List<WarehouseAreaDTO>> getAllWarehouseArea();
    @GetMapping("/{areaId}")
    ResponseEntity<WarehouseAreaDTO> findWarehouseAreaById(@PathVariable("areaId") Long areaId);
    @PostMapping
    ResponseEntity<WarehouseAreaDTO> createWarehouseArea(@RequestBody WarehouseAreaCreateDTO warehouseAreaCreateDTO);
    @DeleteMapping("/{areaId}")
    ResponseEntity<Void> deleteWarehouseArea(@PathVariable("areaId") Long areaId);
    @PutMapping("/{areaId}")
    ResponseEntity<WarehouseAreaDTO> updateWarehouseArea(@PathVariable("areaId")Long areaId,
                                                         @RequestBody WarehouseAreaCreateDTO warehouseAreaCreateDTO);
}
