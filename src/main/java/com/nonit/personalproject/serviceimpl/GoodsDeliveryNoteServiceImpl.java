package com.nonit.personalproject.serviceimpl;

import com.nonit.personalproject.repository.CustomerRepository;
import com.nonit.personalproject.repository.EmployeeRepository;
import com.nonit.personalproject.repository.GoodsDeliveryNoteRepository;
import com.nonit.personalproject.repository.WarehouseAreaRepository;
import com.nonit.personalproject.service.GoodsDeliveryNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodsDeliveryNoteServiceImpl implements GoodsDeliveryNoteService {
    private final GoodsDeliveryNoteRepository goodsDeliveryNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final CustomerRepository customerRepository;
}
