package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;
    @Column(name = "supplier_name", unique = true, nullable = false)
    private String name;
    @Column(name = "supplier_code", unique = true, nullable = false)
    private String code;
    @Column(name = "supplier_email", unique = true, nullable = false)
    private String email;
    @Column(name = "supplier_phone")
    private String phone;
    @Column(name = "supplier_address")
    private String address;
    @Column(name = "supplier_status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
