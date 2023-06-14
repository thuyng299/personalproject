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
public class OutgoingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outgoing_id")
    private Long id;
    @Column(name = "outgoing_amount", nullable = false)
    private Double amount;
    @Column(name = "product_price", nullable = false)
    private Double price;
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "gdn_id", nullable = false)
    private GoodsDeliveryNote goodsDeliveryNote;

    @ManyToOne
    @JoinColumn(name = "incoming_Id", nullable = false)
    private IncomingDetail incomingDetail;
}
