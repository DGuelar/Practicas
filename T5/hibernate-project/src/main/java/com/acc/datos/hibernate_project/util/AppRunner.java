package com.acc.datos.hibernate_project.util;

import com.acc.datos.hibernate_project.servicios.SetupInicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private SetupInicialService setupInicialService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========================================================");
        System.out.println("APLICACIÓN ARRANCADA. EJECUTANDO LÓGICA DE LA ACTIVIDAD 5.5...");
        System.out.println("========================================================");

        try {
            setupInicialService.crearDatosDeEjemplo();
            setupInicialService.verificarDatosCreados();
        } catch (Exception e) {

            Throwable causa = e;

            while (causa != null) {

                if (causa instanceof org.hibernate.exception.ConstraintViolationException) {
                    System.err.println("ERROR: Violación de restricción en la base de datos.");
                    System.err.println("No se pueden insertar datos duplicados.");
                    return;
                }

                causa = causa.getCause();
            }

            System.err.println("ERROR GENERAL: " + e.getMessage());
        }

        System.out.println("========================================================");
        System.out.println("LÓGICA FINALIZADA. La aplicación está lista.");
        System.out.println("========================================================");
    }
}