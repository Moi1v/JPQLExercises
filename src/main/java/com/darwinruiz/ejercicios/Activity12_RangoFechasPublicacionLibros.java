package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Listar libros publicados entre dos fechas (inclusive) con BETWEEN sobre fechaPublicacion.
Ordena por fechaPublicacion DESC.
Sugerencia: usa LocalDate.now().minusDays(...) para variar.
*/
public class Activity12_RangoFechasPublicacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: setParameter("inicio", LocalDate.now().minusDays(10)); setParameter("fin", LocalDate.now())
            // TODO: imprimir resultados
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}