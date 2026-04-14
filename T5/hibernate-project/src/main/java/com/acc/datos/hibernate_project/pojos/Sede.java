package com.acc.datos.hibernate_project.pojos;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sede")
public class Sede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sede")
    private Integer idSede;

    @Column(name = "nom_sede", nullable = false, length = 20)
    private String nomSede;

    public Sede() {
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }
}