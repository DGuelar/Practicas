package com.guelar.proyectoHrSpringBoot.repositorios;

import com.guelar.proyectoHrSpringBoot.dominio.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}