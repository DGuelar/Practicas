package com.guelar.proyectoHrSpringBoot.repositorios;

import com.guelar.proyectoHrSpringBoot.dominio.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}