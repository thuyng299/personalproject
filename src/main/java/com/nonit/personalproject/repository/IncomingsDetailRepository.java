package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.IncomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomingsDetailRepository extends JpaRepository<IncomingsDetail, Long> {
    @Query(value = "select id.product_id, p.product_name, sum(incomings_amount) from goods_received_note grn , incomings_detail id, product p where grn.grn_id = id.grn_id and id.product_id = p.product_id and grn.incomings_date < :date" +
            "group by id.product_id, p.product_name order by id.product_id ", nativeQuery = true)
    List<Object[]> getNumberOfProductIncomings(@Param("date") LocalDate date);

}
