package com.acc.datos.hibernate_project.servicios;

import com.acc.datos.hibernate_project.pojos.Departamento;
import com.acc.datos.hibernate_project.pojos.Empleado;
import com.acc.datos.hibernate_project.pojos.Sede;
import com.acc.datos.hibernate_project.repositorios.DepartamentoRepository;
import com.acc.datos.hibernate_project.repositorios.EmpleadoRepository;
import com.acc.datos.hibernate_project.repositorios.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetupInicialService {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional
    public void crearDatosDeEjemplo() {
        System.out.println("--- CREANDO DATOS DE LA ACTIVIDAD 5.5 ---");

        // Crear nueva sede
        Sede nuevaSede = new Sede();
        nuevaSede.setNomSede("Barcelona Norte");
        Sede sedeGuardada = sedeRepository.saveAndFlush(nuevaSede);

        System.out.println("Sede creada con ID: " + sedeGuardada.getIdSede());

        // Crear dos departamentos para esa sede
        Departamento deptoVentas = new Departamento();
        deptoVentas.setNomDepto("Ventas");
        deptoVentas.setSede(sedeGuardada);
        Departamento deptoVentasGuardado = departamentoRepository.saveAndFlush(deptoVentas);

        System.out.println("Departamento creado con ID: " + deptoVentasGuardado.getIdDepto());

        Departamento deptoRRHH = new Departamento();
        deptoRRHH.setNomDepto("Recursos Humanos");
        deptoRRHH.setSede(sedeGuardada);
        Departamento deptoRRHHGuardado = departamentoRepository.saveAndFlush(deptoRRHH);

        System.out.println("Departamento creado con ID: " + deptoRRHHGuardado.getIdDepto());

        // Crear dos empleados para Ventas
        Empleado emp1 = new Empleado();
        emp1.setDni("11111111A");
        emp1.setNomEmp("Carlos Perez");
        emp1.setDepartamento(deptoVentasGuardado);
        empleadoRepository.save(emp1);

        Empleado emp2 = new Empleado();
        emp2.setDni("22222222B");
        emp2.setNomEmp("Lucia Gomez");
        emp2.setDepartamento(deptoVentasGuardado);
        empleadoRepository.save(emp2);

        // Crear dos empleados para RRHH
        Empleado emp3 = new Empleado();
        emp3.setDni("33333333C");
        emp3.setNomEmp("Marta Ruiz");
        emp3.setDepartamento(deptoRRHHGuardado);
        empleadoRepository.save(emp3);

        Empleado emp4 = new Empleado();
        emp4.setDni("44444444D");
        emp4.setNomEmp("Javier Lopez");
        emp4.setDepartamento(deptoRRHHGuardado);
        empleadoRepository.save(emp4);

        System.out.println("--- DATOS CREADOS CORRECTAMENTE ---");
    }

    @Transactional(readOnly = true)
    public void verificarDatosCreados() {
        System.out.println("--- VERIFICANDO DATOS CREADOS ---");

        List<Sede> sedes = sedeRepository.findAll();
        System.out.println("Total de sedes: " + sedes.size());
        for (Sede sede : sedes) {
            System.out.println("Sede -> ID: " + sede.getIdSede() + ", Nombre: " + sede.getNomSede());
        }

        List<Departamento> departamentos = departamentoRepository.findAll();
        System.out.println("Total de departamentos: " + departamentos.size());
        for (Departamento depto : departamentos) {
            System.out.println("Departamento -> ID: " + depto.getIdDepto()
                    + ", Nombre: " + depto.getNomDepto()
                    + ", ID Sede: " + depto.getSede().getIdSede());
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        System.out.println("Total de empleados: " + empleados.size());
        for (Empleado emp : empleados) {
            System.out.println("Empleado -> DNI: " + emp.getDni()
                    + ", Nombre: " + emp.getNomEmp()
                    + ", ID Depto: " + emp.getDepartamento().getIdDepto());
        }

        System.out.println("--- FIN DE LA VERIFICACIÓN ---");
    }
}