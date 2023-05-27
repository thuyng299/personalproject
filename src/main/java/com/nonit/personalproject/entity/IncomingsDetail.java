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
public class IncomingsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomingsId;
    @Column(name = "incomings_amount", nullable = false)
    private Double incomingsAmount;
    @Column(name = "product_cost", nullable = false)
    private Double productCost;
    @Column(name = "remaining_amount")
    private Double remainingAmount;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "grnId", nullable = false)
    private GoodsReceivedNote goodsReceivedNote;
}
