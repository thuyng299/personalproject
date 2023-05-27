package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.GoodsDeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDeliveryNoteRepository extends JpaRepository<GoodsDeliveryNote, Long> {
}
