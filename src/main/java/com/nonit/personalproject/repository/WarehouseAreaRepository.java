package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.WarehouseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseAreaRepository extends JpaRepository<WarehouseArea, Long> {
    Boolean existsByAreaName (String areaName);
}
