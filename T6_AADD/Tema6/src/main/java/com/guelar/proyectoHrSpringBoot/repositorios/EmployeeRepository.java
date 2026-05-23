package com.guelar.proyectoHrSpringBoot.repositorios;

import com.guelar.proyectoHrSpringBoot.dominio.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(BigDecimal salarioMinimo);

    List<Employee> findByDepartmentIsNull();

    List<Employee> findByLastNameContaining(String texto);
}