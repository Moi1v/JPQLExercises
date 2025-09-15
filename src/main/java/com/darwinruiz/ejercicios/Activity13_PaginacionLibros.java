package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Imprime libros paginando de 5 en 5, recorriendo todas las páginas.
Ordena por id ASC.
*/
public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: bucle con setFirstResult(offset) y setMaxResults(5) hasta que la página venga vacía
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}