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

            // 1) Bulk UPDATE: poner activo = FALSE donde stock = 0
            int librosActualizados = entityManager.createQuery(
                            "UPDATE Libro l SET l.activo = FALSE WHERE l.stock = 0")
                    .executeUpdate();

            System.out.println("=== BULK UPDATE COMPLETADO ===");
            System.out.println("Libros actualizados (activo = FALSE donde stock = 0): " + librosActualizados);

            // 2) Bulk DELETE: eliminar libros con precio < 100
            int librosEliminados = entityManager.createQuery(
                            "DELETE FROM Libro l WHERE l.precio < 100")
                    .executeUpdate();

            System.out.println("\n=== BULK DELETE COMPLETADO ===");
            System.out.println("Libros eliminados (precio < 100): " + librosEliminados);

            System.out.println("\n=== RESUMEN DE OPERACIONES BULK ===");
            System.out.println("Total de libros afectados por UPDATE: " + librosActualizados);
            System.out.println("Total de libros afectados por DELETE: " + librosEliminados);

            entityManager.getTransaction().commit();
            System.out.println("\nOperaciones bulk completadas exitosamente.");

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Error durante las operaciones bulk: " + exception.getMessage());
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}