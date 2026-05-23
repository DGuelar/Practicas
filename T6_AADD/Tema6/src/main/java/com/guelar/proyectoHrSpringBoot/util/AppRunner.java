package com.guelar.proyectoHrSpringBoot.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║          ACTIVIDAD 6.4 — LOMBOK + DDL           ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  Entidades refactorizadas con Lombok:            ║");
        System.out.println("║    @Data → Getters + Setters + toString          ║");
        System.out.println("║    @NoArgsConstructor → Constructor vacío JPA   ║");
        System.out.println("║    @AllArgsConstructor → Constructor completo    ║");
        System.out.println("║    @ToString.Exclude → Evita bucles infinitos    ║");
        System.out.println("║                                                  ║");
        System.out.println("║  Base de datos: hr_prueba_spring (vacía)         ║");
        System.out.println("║  Hibernate ha creado las tablas automáticamente  ║");
        System.out.println("║  Alumno: David Guelar                            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");
    }
}