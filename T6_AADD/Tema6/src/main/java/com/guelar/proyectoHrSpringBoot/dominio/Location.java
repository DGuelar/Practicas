package com.guelar.proyectoHrSpringBoot.dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "street_address", length = 25)
    private String streetAddress;

    @Column(name = "postal_code", length = 12)
    private String postalCode;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(name = "state_province", length = 12)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Country country;
}