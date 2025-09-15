package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

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
            // Definir rango de fechas: últimos 30 años aproximadamente
            LocalDate fechaFin = LocalDate.now();
            LocalDate fechaInicio = LocalDate.now().minusYears(30); // Últimos 30 años

            TypedQuery<Libro> query = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE l.fechaPublicacion BETWEEN :inicio AND :fin ORDER BY l.fechaPublicacion DESC",
                    Libro.class);

            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);

            List<Libro> libros = query.getResultList();

            System.out.println("=== LIBROS PUBLICADOS EN LOS ÚLTIMOS 30 AÑOS ===");
            System.out.println("Rango de fechas: " + fechaInicio + " hasta " + fechaFin);
            System.out.println("Ordenados por fecha de publicación DESC (más recientes primero)");
            System.out.println();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros publicados en el rango de fechas especificado.");
            } else {
                System.out.println("Total de libros encontrados: " + libros.size());
                System.out.println();
                System.out.println("ID | Título | Autor | Fecha Publicación | Precio | Stock");
                System.out.println("=".repeat(90));

                for (Libro libro : libros) {
                    System.out.printf("%3d | %-30s | %-20s | %10s | $%7.2f | %5d%n",
                            libro.getId(),
                            truncarTexto(libro.getTitulo(), 30),
                            truncarTexto(libro.getAutorNombre(), 20),
                            libro.getFechaPublicacion(),
                            libro.getPrecio(),
                            libro.getStock());
                }

                // Estadísticas adicionales
                LocalDate fechaMasReciente = libros.get(0).getFechaPublicacion();
                LocalDate fechaMasAntigua = libros.get(libros.size() - 1).getFechaPublicacion();

                System.out.println("\n=== ESTADÍSTICAS DEL RANGO ===");
                System.out.println("Libro más reciente: " + fechaMasReciente);
                System.out.println("Libro más antiguo en el rango: " + fechaMasAntigua);

                // Agrupar por década
                System.out.println("\n=== LIBROS POR DÉCADA ===");
                libros.stream()
                        .collect(java.util.stream.Collectors.groupingBy(
                                libro -> (libro.getFechaPublicacion().getYear() / 10) * 10,
                                java.util.stream.Collectors.counting()))
                        .entrySet().stream()
                        .sorted(java.util.Map.Entry.<Integer, Long>comparingByKey().reversed())
                        .forEach(entry -> System.out.println(entry.getKey() + "s: " + entry.getValue() + " libros"));
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