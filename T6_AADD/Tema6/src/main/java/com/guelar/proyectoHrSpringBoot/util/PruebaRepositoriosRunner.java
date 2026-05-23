package com.guelar.proyectoHrSpringBoot.util;

import com.guelar.proyectoHrSpringBoot.dominio.Employee;
import com.guelar.proyectoHrSpringBoot.repositorios.DepartmentRepository;
import com.guelar.proyectoHrSpringBoot.repositorios.EmployeeRepository;
import com.guelar.proyectoHrSpringBoot.repositorios.JobRepository;
import com.guelar.proyectoHrSpringBoot.repositorios.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PruebaRepositoriosRunner implements CommandLineRunner {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) {

        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  PRUEBA DE REPOSITORIOS — David Guelar");
        System.out.println("══════════════════════════════════════════════════");

        System.out.println("\n📊 TOTALES EN BASE DE DATOS:");
        System.out.println("  Regiones:      " + regionRepository.count());
        System.out.println("  Empleados:     " + employeeRepository.count());
        System.out.println("  Departamentos: " + departmentRepository.count());
        System.out.println("  Trabajos:      " + jobRepository.count());

        System.out.println("\n🌍 LISTADO DE REGIONES:");
        regionRepository.findAll().forEach(r ->
                System.out.println("  -> " + r.getRegionName()));

        BigDecimal corte = new BigDecimal("10000");
        List<Employee> altaSalario = employeeRepository.findBySalaryGreaterThan(corte);
        System.out.println("\n💰 EMPLEADOS CON SALARIO > " + corte + " (" + altaSalario.size() + "):");
        altaSalario.forEach(e ->
                System.out.printf("  -> %-20s [%s]\n",
                        e.getFirstName() + " " + e.getLastName(), e.getSalary()));

        String apellido = "King";
        List<Employee> porApellido = employeeRepository.findByLastNameContaining(apellido);
        System.out.println("\n🔍 EMPLEADOS CON APELLIDO QUE CONTIENE '" + apellido + "':");
        porApellido.forEach(e ->
                System.out.println("  -> " + e.getFirstName() + " " + e.getLastName()));

        List<Employee> sinDepto = employeeRepository.findByDepartmentIsNull();
        System.out.println("\n⚠️  EMPLEADOS SIN DEPARTAMENTO ASIGNADO: " + sinDepto.size());

        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  FIN DE PRUEBA — http://localhost:8080");
        System.out.println("══════════════════════════════════════════════════\n");
    }
}