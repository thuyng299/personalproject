//package com.nonit.personalproject.security.service.impl;
//
//import com.nonit.personalproject.entity.Employee;
//import com.nonit.personalproject.repository.EmployeeRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final EmployeeRepository employeeRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Employee employee = employeeRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with username: " + username));
//
//        return UserDetailsImpl.build(employee);
//    }
//
//    @Transactional
//    public UserDetails validateUser(String username) {
//        Employee employee = employeeRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with username: " + username));
//
//        return UserDetailsImpl.build(employee);
//    }
//}
