package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryId implements Serializable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "start_date")
    private LocalDate startDate;
}