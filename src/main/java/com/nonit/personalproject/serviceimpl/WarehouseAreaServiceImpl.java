package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
import com.nonit.personalproject.dto.WarehouseAreaDTO;
import com.nonit.personalproject.entity.WarehouseArea;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.mapper.WarehouseAreaMapper;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.WarehouseAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseAreaServiceImpl implements WarehouseAreaService {
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final WarehouseAreaMapper warehouseAreaMapper = WarehouseAreaMapper.INSTANCE;
    @Override
    public List<WarehouseAreaDTO> getAllWarehouseArea() {
        List<WarehouseArea> warehouseAreas = warehouseAreaRepository.findAll();
        if (warehouseAreas.isEmpty()){
            throw WarehouseException.WarehouseAreaNotFound();
        }
        return warehouseAreaMapper.toDtos(warehouseAreas);
    }

    @Override
    public WarehouseAreaDTO findWarehouseAreaById(Long areaId) {
        WarehouseArea warehouseArea = warehouseAreaRepository.findById(areaId).orElseThrow(WarehouseException::WarehouseAreaNotFound);
        return warehouseAreaMapper.toDto(warehouseArea);
    }

    @Override
    public WarehouseAreaDTO createWarehouseArea(WarehouseAreaCreateDTO warehouseAreaCreateDTO) {
        if (warehouseAreaCreateDTO.getAreaName() == null || warehouseAreaCreateDTO.getAreaName().isBlank() || warehouseAreaCreateDTO.getAreaName().isEmpty()){
            throw WarehouseException.badRequest("InvalidAreaName", "Warehouse area name cannot be null!");
        }
        if (warehouseAreaRepository.existsByAreaName(warehouseAreaCreateDTO.getAreaName())){
            throw WarehouseException.badRequest("AreaNameExisted", "Area name already exists!");
        }
       WarehouseArea warehouseArea = WarehouseArea.builder()
               .areaName(warehouseAreaCreateDTO.getAreaName())
               .build();
       warehouseArea = warehouseAreaRepository.save(warehouseArea);
        return warehouseAreaMapper.toDto(warehouseArea);
    }

    @Override
    public void deleteWarehouseArea(Long areaId) {
        log.info("delete warehouse area by id {}", areaId);
        warehouseAreaRepository.deleteById(areaId);
    }
}
