package com.darwinruiz.ejercicios;

import com.darwinruiz.dto.LibroResumenDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
Proyecta Libros a LibroResumenDto (id, titulo, precio) usando:
SELECT new com.example.jpql_exercises.dto.LibroResumenDto(l.id, l.titulo, l.precio)
Ordena por id ASC e imprime.
*/
public class Activity07_ProyeccionLibrosDto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<LibroResumenDto> query = entityManager.createQuery(
                    "SELECT new com.darwinruiz.dto.LibroResumenDto(l.id, l.titulo, l.precio) " +
                            "FROM Libro l " +
                            "ORDER BY l.id ASC",
                    LibroResumenDto.class);

            List<LibroResumenDto> librosResumen = query.getResultList();

            System.out.println("=== PROYECCIÓN DE LIBROS A DTO (ID, TÍTULO, PRECIO) ===");
            System.out.println("ID | Título | Precio");
            System.out.println("=".repeat(80));

            for (LibroResumenDto libroDto : librosResumen) {
                System.out.println(libroDto);
            }

            System.out.println("\nTotal de libros proyectados: " + librosResumen.size());

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}