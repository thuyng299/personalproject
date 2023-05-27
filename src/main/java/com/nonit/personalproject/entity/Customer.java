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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "customer_name", unique = true, nullable = false)
    private String customerName;
    @Column(name = "customer_code", unique = true, nullable = false)
    private String customerCode;
    @Column(name = "customer_email", unique = true, nullable = false)
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;

    @ManyToOne
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;
}
