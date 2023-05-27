package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gdnId;
    @Column(name = "outcomings_date", nullable = false)
    private LocalDate outcomingsDate;

    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "areaId", nullable = false)
    private WarehouseArea warehouseArea;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
}
