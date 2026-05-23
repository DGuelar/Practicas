package com.guelar.proyectoHrSpringBoot.repositorios;

import com.guelar.proyectoHrSpringBoot.dominio.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
}