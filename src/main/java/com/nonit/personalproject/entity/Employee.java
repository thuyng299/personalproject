package com.nonit.personalproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;
    @Column(name = "employee_address", nullable = false)
    private String address;
    @Column(name = "salary", nullable = false)
    private Double salary;
    @Column(name = "employee_position", nullable = false)
    private String position;
    @Column(name = "employee_status", nullable = false)
    private Boolean status;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private WarehouseArea warehouseArea;
}
