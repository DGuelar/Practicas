package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {

    @EmbeddedId
    private JobHistoryId id;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;
}