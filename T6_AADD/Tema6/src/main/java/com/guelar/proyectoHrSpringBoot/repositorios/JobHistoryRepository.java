package com.guelar.proyectoHrSpringBoot.repositorios;

import com.guelar.proyectoHrSpringBoot.dominio.JobHistory;
import com.guelar.proyectoHrSpringBoot.dominio.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {
}