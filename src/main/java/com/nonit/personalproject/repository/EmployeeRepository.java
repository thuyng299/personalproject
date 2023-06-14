//package com.nonit.personalproject.repository;
//
//import com.nonit.personalproject.entity.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    List<Employee> findByFirstName(String firstName);
//    Optional<Employee> findByUsername(String userName);
//    Boolean existsByEmail(String email);
//    Boolean existsByUsername(String userName);
//    Boolean existsByPassword (String password);
//    Boolean existsByPhone (String phone);
//
//}
