//package com.nonit.personalproject.rest;
//
//import com.nonit.personalproject.dto.WarehouseAreaCreateDTO;
//import com.nonit.personalproject.dto.WarehouseAreaDTO;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@PreAuthorize("hasAnyRole('USER', 'WAREHOUSE_STAFF')")
//@RequestMapping(value = "/warehouseareas")
//public interface WarehouseAreaAPI {
//    @GetMapping // localhost:8080/warehouseareas
//    ResponseEntity<List<WarehouseAreaDTO>> getAllWarehouseArea();
//
//    @GetMapping("/{areaId}") // localhost:8080/warehouseareas/2
//    ResponseEntity<WarehouseAreaDTO> findWarehouseAreaById(@PathVariable("areaId") Long areaId);
//
//    @PostMapping
//    ResponseEntity<WarehouseAreaDTO> createWarehouseArea(@RequestBody WarehouseAreaCreateDTO warehouseAreaCreateDTO);
//
//    @DeleteMapping("/{areaId}")
//    ResponseEntity<Void> deleteWarehouseArea(@PathVariable("areaId") Long areaId);
//
//    @PutMapping("/{areaId}")
//    ResponseEntity<WarehouseAreaDTO> updateWarehouseArea(@PathVariable("areaId")Long areaId,
//                                                         @RequestBody WarehouseAreaCreateDTO warehouseAreaCreateDTO);
//}
