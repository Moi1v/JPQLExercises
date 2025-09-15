package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Consulta clientes cuyo nombre contenga 'a' (case-insensitive),
ordena por ciudad ASC, y devuelve la página 2 (tamaño 3).
Imprime los resultados.
*/
public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: crear TypedQuery con LOWER(nombre) LIKE :patron, ORDER BY ciudad
            // TODO: setFirstResult(3) y setMaxResults(3)
            // TODO: imprimir resultados
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}