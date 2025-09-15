package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Consulta clientes cuya ciudad esté en una lista (IN :ciudades),
por ejemplo: ["Guatemala","Antigua","Cobán"].
Imprime resultados.
*/
public class Activity14_IN_ListaDeCiudadesClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: List<String> ciudades = Arrays.asList("Guatemala","Antigua","Cobán");
            // TODO: SELECT c FROM Cliente c WHERE c.ciudad IN :ciudades
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}