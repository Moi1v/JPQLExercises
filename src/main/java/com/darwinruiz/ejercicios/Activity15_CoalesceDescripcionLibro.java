package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

/*
ENUNCIADO:
Selecciona titulo y una descripcion segura usando COALESCE(descripcion, 'Sin descripción').
Imprime "Titulo | DescripcionSegura".
*/
public class Activity15_CoalesceDescripcionLibro {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery(
                    "SELECT l.titulo, COALESCE(l.descripcion, 'La obra cumbre de la literatura española') FROM Libro l ORDER BY l.titulo");

            List<Object[]> resultados = query.getResultList();

            System.out.println("=== LIBROS CON DESCRIPCIÓN SEGURA (COALESCE) ===");
            System.out.println("Usando COALESCE para evitar valores NULL en descripción");
            System.out.println("Ordenados por título ASC");
            System.out.println();

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros en la base de datos.");
            } else {
                System.out.println("Total de libros: " + resultados.size());
                System.out.println();
                System.out.println("Título | Descripción Segura");
                System.out.println("=".repeat(80));

                int contadorSinDescripcion = 0;
                int contadorConDescripcion = 0;

                for (Object[] fila : resultados) {
                    String titulo = (String) fila[0];
                    String descripcionSegura = (String) fila[1];

                    // Contar cuántos tienen descripción real vs placeholder
                    if ("Sin descripción".equals(descripcionSegura)) {
                        contadorSinDescripcion++;
                    } else {
                        contadorConDescripcion++;
                    }

                    System.out.printf("%-35s | %s%n",
                            truncarTexto(titulo, 35),
                            descripcionSegura);
                }

                // Estadísticas
                System.out.println("\n" + "=".repeat(50));
                System.out.println("ESTADÍSTICAS DE DESCRIPCIONES");
                System.out.println("=".repeat(50));
                System.out.printf("Libros con descripción real: %d%n", contadorConDescripcion);
                System.out.printf("Libros sin descripción (NULL): %d%n", contadorSinDescripcion);
                System.out.printf("Total de libros: %d%n", resultados.size());

                double porcentajeConDescripcion = (contadorConDescripcion * 100.0) / resultados.size();
                System.out.printf("Porcentaje con descripción: %.1f%%%n", porcentajeConDescripcion);

                // Mostrar ejemplos de cada tipo
                System.out.println("\n=== EJEMPLOS ===");
                System.out.println("Libros CON descripción:");
                resultados.stream()
                        .filter(fila -> !"Sin descripción".equals(fila[1]))
                        .limit(3)
                        .forEach(fila -> System.out.println("• " + fila[0]));

                System.out.println("\nLibros SIN descripción (mostrando placeholder):");
                resultados.stream()
                        .filter(fila -> "Sin descripción".equals(fila[1]))
                        .limit(3)
                        .forEach(fila -> System.out.println("• " + fila[0]));
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