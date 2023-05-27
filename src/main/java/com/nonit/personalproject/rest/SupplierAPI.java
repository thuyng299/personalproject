package com.nonit.personalproject.rest;

import com.nonit.personalproject.dto.SupplierCreateDTO;
import com.nonit.personalproject.dto.SupplierDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/suppliers")
public interface SupplierAPI {
    @GetMapping
    ResponseEntity<List<SupplierDTO>> getAllSupplier();
    @GetMapping("/{supplierId}")
    ResponseEntity<SupplierDTO> findSupplierById(@PathVariable("supplierId") Long supplierId);
    @PostMapping("/{countryId}")
    ResponseEntity<SupplierDTO> createSupplier(@PathVariable("countryId") Long countryId,
                                               @RequestBody SupplierCreateDTO supplierCreateDTO);
    @DeleteMapping("/{supplierId}")
    ResponseEntity<Void> deleteSupplier(@PathVariable("supplierId") Long supplierId);
    @GetMapping("/name")
    ResponseEntity<SupplierDTO> findBySupplierName(@RequestParam("supplierName") String supplierName);

}
