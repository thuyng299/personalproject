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
    private Long supplierId;
    @Column(name = "supplier_name", unique = true, nullable = false)
    private String supplierName;
    @Column(name = "supplier_code", unique = true, nullable = false)
    private String supplierCode;
    @Column(name = "supplier_email", unique = true, nullable = false)
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    @ManyToOne()
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;

}
