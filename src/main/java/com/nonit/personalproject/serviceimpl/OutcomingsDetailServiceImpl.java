package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.dto.OutgoingAmountStatsDTO;
import com.nonit.personalproject.entity.GoodsDeliveryNote;
import com.nonit.personalproject.entity.OutcomingsDetail;
import com.nonit.personalproject.exception.WarehouseException;
import com.nonit.personalproject.repository.GoodsDeliveryNoteRepository;
import com.nonit.personalproject.repository.IncomingsDetailRepository;
import com.nonit.personalproject.repository.OutcomingsDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.service.OutcomingsDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutcomingsDetailServiceImpl implements OutcomingsDetailService {
    private final OutcomingsDetailRepository outcomingsDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final IncomingsDetailRepository incomingsDetailRepository;

    @Override
    public List<OutgoingAmountStatsDTO> getNumberOfProductOutgoings(LocalDate date) {
        if (date.isAfter(LocalDate.now())){
            throw WarehouseException.badRequest("InvalidDate", "Date must be before " + LocalDate.now());
        }
        return outcomingsDetailRepository.getNumberOfProductOutgoings(date);
    }
}
