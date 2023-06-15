package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaDTO;
import com.nonit.personalproject.serviceimpl.WarehouseAreaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WarehouseAreaResource implements WarehouseAreaAPI{
    private final WarehouseAreaServiceImpl warehouseAreaServiceImpl;

    @Override
    public ResponseEntity<List<WarehouseAreaDTO>> getAllWarehouseArea() {
        return ResponseEntity.ok(warehouseAreaServiceImpl.getAllWarehouseArea());
    }

    @Override
    public ResponseEntity<WarehouseAreaDTO> findWarehouseAreaById(Long areaId) {
        return ResponseEntity.ok(warehouseAreaServiceImpl.findWarehouseAreaById(areaId));
    }

    @Override
    public ResponseEntity<WarehouseAreaDTO> createWarehouseArea(WarehouseAreaCreateDTO warehouseAreaCreateDTO) {
        WarehouseAreaDTO createdwarehouseAreaDTO = warehouseAreaServiceImpl.createWarehouseArea(warehouseAreaCreateDTO);
        return ResponseEntity.created(URI.create("/warehouseareas" + createdwarehouseAreaDTO.getId())).body(createdwarehouseAreaDTO);
    }

    @Override
    public ResponseEntity<Void> deleteWarehouseArea(Long areaId) {
        warehouseAreaServiceImpl.deleteWarehouseArea(areaId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<WarehouseAreaDTO> updateWarehouseArea(Long areaId, WarehouseAreaCreateDTO warehouseAreaCreateDTO) {
        return ResponseEntity.ok().body(warehouseAreaServiceImpl.updateWarehouseArea(areaId, warehouseAreaCreateDTO));
    }
}
