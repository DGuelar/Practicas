package com.acc.datos.hibernate_project.pojos;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nom_emp", nullable = false, length = 40)
    private String nomEmp;

    @ManyToOne
    @JoinColumn(name = "id_depto", nullable = false)
    private Departamento departamento;

    public Empleado() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}