package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Consulta libros con precio entre 150 y 800 (inclusive) usando BETWEEN.
Ordena por precio ASC e imprime.
*/
public class Activity11_RangosPrecioBetweenLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: setParameter("min", new BigDecimal("150")) y ("max", new BigDecimal("800"))
            // TODO: imprimir resultados
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}