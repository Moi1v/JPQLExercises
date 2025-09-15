package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
            // TODO: crear query con SELECT new ... LibroResumenDto(...), getResultList, imprimir
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
