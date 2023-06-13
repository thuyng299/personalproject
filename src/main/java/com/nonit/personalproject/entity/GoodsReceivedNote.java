package com.nonit.personalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsReceivedNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grnId;
    @Column(name = "incomings_date", nullable = false)
    private LocalDate incomingsDate;

    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "areaId", nullable = false)
    private WarehouseArea warehouseArea;

    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;

    @OneToOne(mappedBy = "goodsReceivedNote", cascade = CascadeType.PERSIST)
    private IncomingsDetail incomingsDetail;
}
