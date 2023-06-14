package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gdn_id")
    private Long id;
    @Column(name = "gdn_code", unique = true, nullable = false)
    private String code;
    @Column(name = "outgoing_date", nullable = false)
    private LocalDateTime outgoingDate;
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    @Column(name = "gdn_record", length = 500)
    private String record;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
