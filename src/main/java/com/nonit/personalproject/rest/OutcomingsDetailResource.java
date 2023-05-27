package com.nonit.personalproject.rest;

import com.nonit.personalproject.serviceimpl.OutcomingsDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OutcomingsDetailResource implements OutcomingsDetailAPI{
    private final OutcomingsDetailServiceImpl outcomingsDetailServiceImpl;
}
