package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.entity.OutcomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OutcomingsDetailRepository extends JpaRepository<OutcomingsDetail, Long> {
    @Query("select new com.nonit.personalproject.dto.OutgoingAmountStatsDTO (od.product.productId, p.productName, sum(od.outcomingsAmount)) from GoodsDeliveryNote gdn, OutcomingsDetail od, Product p where gdn.gdnId = od.goodsDeliveryNote.gdnId and p.productId = od.product.productId and gdn.outcomingsDate < :date group by od.product.productId, p.productName order by od.product.productId")
    List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(@Param("date") LocalDate date);

}
