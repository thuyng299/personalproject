package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incoming_id")
    private Long id;
    @Column(name = "incoming_amount", nullable = false)
    private Double amount;
    @Column(name = "product_cost", nullable = false)
    private Double cost;
    @Column(name = "remaining_amount")
    private Double remainingAmount;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private WarehouseArea warehouseArea;
    @ManyToOne
    @JoinColumn(name = "grn_id", nullable = false)
    private GoodsReceivedNote goodsReceivedNote;
}
