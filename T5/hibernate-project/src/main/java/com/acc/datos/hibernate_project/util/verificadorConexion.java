package com.acc.datos.hibernate_project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@Component
public class verificadorConexion implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("========================================================");
        System.out.println("INICIANDO PRUEBA DE CONEXIÓN A LA BASE DE DATOS...");
        System.out.println("========================================================");

        try (Connection connection = dataSource.getConnection()) {

            if (connection != null) {
                System.out.println("¡CONEXIÓN ESTABLECIDA CON ÉXITO!");

                DatabaseMetaData metaData = connection.getMetaData();

                System.out.println(" -> URL de la BBDD: " + metaData.getURL());
                System.out.println(" -> Usuario: " + metaData.getUserName());
                System.out.println(" -> Driver: " + metaData.getDriverName());

                System.out.println("\n--- OBTENIENDO ESQUEMA DE TABLAS ---");

                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("\n[TABLA]: " + tableName.toUpperCase());

                    ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, "%");

                    while (columns.next()) {
                        String columnName = columns.getString("COLUMN_NAME");
                        String columnType = columns.getString("TYPE_NAME");
                        int columnSize = columns.getInt("COLUMN_SIZE");

                        System.out.printf(" - Columna: %-25s | Tipo: %-15s | Tamaño: %d%n",
                                columnName, columnType, columnSize);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("ERROR AL CONECTAR CON LA BASE DE DATOS: " + e.getMessage());
        }

        System.out.println("\n========================================================");
        System.out.println("PRUEBA DE CONEXIÓN FINALIZADA. La aplicación continuará su arranque.");
        System.out.println("========================================================");
    }
}