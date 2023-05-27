package com.nonit.personalproject.repository;

import com.nonit.personalproject.dto.IncomingsAmountStatsDTO;
import com.nonit.personalproject.entity.IncomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomingsDetailRepository extends JpaRepository<IncomingsDetail, Long> {
    @Query("select new com.nonit.personalproject.dto.IncomingsAmountStatsDTO (id.product.productId, p.productName, sum(id.incomingsAmount)) from GoodsReceivedNote grn, IncomingsDetail id, Product p where grn.grnId = id.goodsReceivedNote.grnId and p.productId = id.product.productId and grn.incomingsDate < :date group by id.product.productId, p.productName order by id.product.productId")
    List<IncomingsAmountStatsDTO> getNumberOfProductIncomings(@Param("date") LocalDate date);

}
