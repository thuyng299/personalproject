package com.nonit.personalproject.rest;

import com.nonit.personalproject.serviceimpl.GoodsDeliveryNoteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GoodsDeliveryNoteResource implements GoodsDeliveryNoteAPI {
    private final GoodsDeliveryNoteServiceImpl goodsDeliveryNoteServiceImpl;
}
