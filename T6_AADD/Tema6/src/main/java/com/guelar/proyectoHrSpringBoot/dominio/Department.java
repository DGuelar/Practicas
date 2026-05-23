package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name", nullable = false, length = 30)
    private String departmentName;

    @Column(name = "manager_id")
    private Long managerId;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location location;
}