package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.repository.GoodsDeliveryNoteRepository;
import com.nonit.personalproject.repository.IncomingsDetailRepository;
import com.nonit.personalproject.repository.OutcomingsDetailRepository;
import com.nonit.personalproject.repository.ProductRepository;
import com.nonit.personalproject.service.OutcomingsDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutcomingsDetailServiceImpl implements OutcomingsDetailService {
    private final OutcomingsDetailRepository outcomingsDetailRepository;
    private final ProductRepository productRepository;
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final IncomingsDetailRepository incomingsDetailRepository;
}
