package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.math.BigDecimal;

/*
ENUNCIADO:
1) Inserta al menos 3 clientes y 3 libros con datos realistas.
2) Varía fechas (fechaRegistro y fechaPublicacion), genera 1 libro con descripcion = NULL y 1 con stock = 0.
3) Imprime los IDs generados.
Restricciones:
- Usa tipos explícitos (sin var).
- Maneja transacción (begin/commit/rollback).
*/
public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Crear 3 clientes con datos realistas y fechas variadas
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("María González");
            cliente1.setEmail("maria.gonzalez@ejmplo.com");
            cliente1.setCiudad("Guatemala");
            cliente1.setEdad(28);
            cliente1.setActivo(true);
            cliente1.setFechaRegistro(LocalDate.of(2023, 3, 15));
            entityManager.persist(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Carlos Rodríguez");
            cliente2.setEmail("carlos.rodriguez@ejemplo.com");
            cliente2.setCiudad("Antigua");
            cliente2.setEdad(35);
            cliente2.setActivo(true);
            cliente2.setFechaRegistro(LocalDate.of(2023, 8, 22));
            entityManager.persist(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Ana Martínez");
            cliente3.setEmail("ana.martinez@ejemplo.com");
            cliente3.setCiudad("Quetzaltenango");
            cliente3.setEdad(42);
            cliente3.setActivo(false);
            cliente3.setFechaRegistro(LocalDate.of(2024, 1, 10));
            entityManager.persist(cliente3);

            // Crear 3 libros con datos realistas y variaciones específicas
            // Libro 1: Normal
            Libro libro1 = new Libro();
            libro1.setTitulo("El Quijote de la Mancha");
            libro1.setAutorNombre("Miguel de Cervantes");
            libro1.setGenero("Clásico");
            libro1.setPrecio(new BigDecimal("25.50"));
            libro1.setStock(15);
            libro1.setActivo(true);
            libro1.setDescripcion("La obra cumbre de la literatura española");
            libro1.setFechaPublicacion(LocalDate.of(1605, 1, 16));
            entityManager.persist(libro1);

            // Libro 2: Con descripción NULL
            Libro libro2 = new Libro();
            libro2.setTitulo("Cien Años de Soledad");
            libro2.setAutorNombre("Gabriel García Márquez");
            libro2.setGenero("Realismo Mágico");
            libro2.setPrecio(new BigDecimal("22.90"));
            libro2.setStock(8);
            libro2.setActivo(true);
            libro2.setDescripcion(null); // Descripción NULL como se solicita
            libro2.setFechaPublicacion(LocalDate.of(1967, 5, 30));
            entityManager.persist(libro2);

            // Libro 3: Con stock = 0
            Libro libro3 = new Libro();
            libro3.setTitulo("1984");
            libro3.setAutorNombre("George Orwell");
            libro3.setGenero("Distopía");
            libro3.setPrecio(new BigDecimal("18.75"));
            libro3.setStock(0); // Stock 0 como se solicita
            libro3.setActivo(true);
            libro3.setDescripcion("Una distopía sobre el control totalitario");
            libro3.setFechaPublicacion(LocalDate.of(1949, 6, 8));
            entityManager.persist(libro3);

            entityManager.getTransaction().commit();

            // Imprimir IDs generados
            System.out.println("=== IDs GENERADOS ===");
            System.out.println("CLIENTES:");
            System.out.println("Cliente 1 - ID: " + cliente1.getId() + " - " + cliente1.getNombre() + " (" + cliente1.getCiudad() + ")");
            System.out.println("Cliente 2 - ID: " + cliente2.getId() + " - " + cliente2.getNombre() + " (" + cliente2.getCiudad() + ")");
            System.out.println("Cliente 3 - ID: " + cliente3.getId() + " - " + cliente3.getNombre() + " (" + cliente3.getCiudad() + ")");

            System.out.println("\nLIBROS:");
            System.out.println("Libro 1 - ID: " + libro1.getId() + " - " + libro1.getTitulo() + " (Stock: " + libro1.getStock() + ")");
            System.out.println("Libro 2 - ID: " + libro2.getId() + " - " + libro2.getTitulo() + " (Descripción: " +
                    (libro2.getDescripcion() == null ? "NULL" : libro2.getDescripcion()) + ")");
            System.out.println("Libro 3 - ID: " + libro3.getId() + " - " + libro3.getTitulo() + " (Stock: " + libro3.getStock() + ")");

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println("Error durante la transacción: " + exception.getMessage());
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}