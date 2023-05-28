package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutcomingsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outcomingsId;
    @Column(name = "outcomings_amount", nullable = false)
    private Double outcomingsAmount;
    @Column(name = "product_price", nullable = false)
    private Double productPrice;
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "gdnId", nullable = false)
    private GoodsDeliveryNote goodsDeliveryNote;

    @ManyToOne
    @JoinColumn(name = "incomingsId", nullable = false)
    private IncomingsDetail incomingsDetail;
}
