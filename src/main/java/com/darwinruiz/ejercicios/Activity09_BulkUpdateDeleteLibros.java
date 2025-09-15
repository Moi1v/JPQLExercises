package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
1) Bulk UPDATE: poner activo = FALSE donde stock = 0.
2) Bulk DELETE: eliminar libros con precio < 100.
Imprime cantidades afectadas.
*/
public class Activity09_BulkUpdateDeleteLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: UPDATE Libro l SET l.activo = FALSE WHERE l.stock = 0
            // TODO: DELETE FROM Libro l WHERE l.precio < 100

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}