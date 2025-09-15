package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
1) Lista todos los clientes ordenados por nombre (JPQL).
2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
3) Imprime ambos resultados.
*/
public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT c FROM Cliente c ORDER BY c.nombre
            // TODO: if (!lista.isEmpty()) -> find por id y print
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}