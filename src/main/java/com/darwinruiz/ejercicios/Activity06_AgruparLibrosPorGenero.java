package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Agrupa libros por género y muestra:
- COUNT(*), AVG(precio), SUM(stock)
Ordena por género ASC.
*/
public class Activity06_AgruparLibrosPorGenero {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) FROM Libro l GROUP BY l.genero ORDER BY l.genero
            // TODO: imprimir filas
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}