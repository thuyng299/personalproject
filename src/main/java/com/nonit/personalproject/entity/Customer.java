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
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name", unique = true, nullable = false)
    private String name;
    @Column(name = "customer_code", unique = true, nullable = false)
    private String code;
    @Column(name = "customer_email", unique = true, nullable = false)
    private String email;
    @Column(name = "customer_phone")
    private String phone;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
