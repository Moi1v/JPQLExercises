package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

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
            TypedQuery<Libro> query = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE l.precio BETWEEN :min AND :max ORDER BY l.precio ASC",
                    Libro.class);

            query.setParameter("min", new BigDecimal("150"));
            query.setParameter("max", new BigDecimal("800"));

            List<Libro> libros = query.getResultList();

            System.out.println("=== LIBROS CON PRECIO ENTRE $150.00 Y $800.00 ===");
            System.out.println("Rango: $150.00 - $800.00 (inclusive)");
            System.out.println("Ordenados por precio ASC");
            System.out.println();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en el rango de precios especificado.");
            } else {
                System.out.println("Total de libros encontrados: " + libros.size());
                System.out.println();
                System.out.println("ID | Título | Autor | Género | Precio | Stock | Activo");
                System.out.println("=".repeat(100));

                for (Libro libro : libros) {
                    System.out.printf("%3d | %-35s | %-20s | %-15s | $%7.2f | %5d | %s%n",
                            libro.getId(),
                            truncarTexto(libro.getTitulo(), 35),
                            truncarTexto(libro.getAutorNombre(), 20),
                            libro.getGenero(),
                            libro.getPrecio(),
                            libro.getStock(),
                            libro.getActivo() ? "Sí" : "No");
                }

                // Estadísticas adicionales
                BigDecimal precioMinimo = libros.get(0).getPrecio();
                BigDecimal precioMaximo = libros.get(libros.size() - 1).getPrecio();

                System.out.println("\n=== ESTADÍSTICAS ===");
                System.out.println("Precio mínimo encontrado: $" + precioMinimo);
                System.out.println("Precio máximo encontrado: $" + precioMaximo);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private static String truncarTexto(String texto, int maxLength) {
        if (texto == null) return "";
        return texto.length() > maxLength ? texto.substring(0, maxLength - 3) + "..." : texto;
    }
}