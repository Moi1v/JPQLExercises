package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.util.List;

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
            Query query = entityManager.createQuery(
                    "SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) " +
                            "FROM Libro l " +
                            "GROUP BY l.genero " +
                            "ORDER BY l.genero ASC");

            List<Object[]> resultados = query.getResultList();

            System.out.println("=== LIBROS AGRUPADOS POR GÉNERO ===");
            System.out.println("Género | Cantidad | Precio Promedio | Stock Total");
            System.out.println("=".repeat(60));

            for (Object[] fila : resultados) {
                String genero = (String) fila[0];
                Long cantidad = (Long) fila[1];
                Double precioPromedio = (Double) fila[2];
                Long stockTotal = (Long) fila[3];

                System.out.printf("%-15s | %8d | $%13.2f | %10d%n",
                        genero, cantidad, precioPromedio, stockTotal);
            }

            System.out.println("\nTotal de géneros encontrados: " + resultados.size());

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}