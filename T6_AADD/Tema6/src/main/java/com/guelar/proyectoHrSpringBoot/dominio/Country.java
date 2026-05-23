package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "country_id", length = 2)
    private String id;

    @Column(name = "country_name", length = 40)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Region region;
}