package com.nonit.personalproject.repository;

import com.nonit.personalproject.entity.OutcomingsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomingsDetailRepository extends JpaRepository<OutcomingsDetail, Long> {
}
