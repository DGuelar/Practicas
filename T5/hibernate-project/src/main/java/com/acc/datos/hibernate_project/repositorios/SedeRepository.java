package com.acc.datos.hibernate_project.repositorios;

import com.acc.datos.hibernate_project.pojos.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
}