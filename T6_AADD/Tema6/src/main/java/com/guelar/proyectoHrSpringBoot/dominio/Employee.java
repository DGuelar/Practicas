package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, unique = true, length = 25)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Job job;

    @Column(precision = 8, scale = 2)
    private BigDecimal salary;

    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;
}