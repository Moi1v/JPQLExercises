package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
Imprime libros paginando de 5 en 5, recorriendo todas las páginas.
Ordena por id ASC.
*/
public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        final int TAMANO_PAGINA = 5;
        int paginaActual = 1;
        int offset = 0;
        int totalLibrosProcesados = 0;

        try {
            System.out.println("=== PAGINACIÓN DE LIBROS (5 POR PÁGINA) ===");
            System.out.println("Ordenados por ID ASC");
            System.out.println();

            while (true) {
                TypedQuery<Libro> query = entityManager.createQuery(
                        "SELECT l FROM Libro l ORDER BY l.id ASC", Libro.class);

                query.setFirstResult(offset);
                query.setMaxResults(TAMANO_PAGINA);

                List<Libro> librosPagina = query.getResultList();

                // Si no hay más resultados, salir del bucle
                if (librosPagina.isEmpty()) {
                    System.out.println("No hay más páginas. Paginación completada.");
                    break;
                }

                // Mostrar encabezado de página
                System.out.println("=".repeat(80));
                System.out.printf("PÁGINA %d (Registros %d-%d)%n",
                        paginaActual,
                        offset + 1,
                        offset + librosPagina.size());
                System.out.println("=".repeat(80));
                System.out.println("ID | Título | Autor | Género | Precio | Stock");
                System.out.println("-".repeat(80));

                // Mostrar libros de la página actual
                for (Libro libro : librosPagina) {
                    System.out.printf("%3d | %-25s | %-15s | %-10s | $%6.2f | %3d%n",
                            libro.getId(),
                            truncarTexto(libro.getTitulo(), 25),
                            truncarTexto(libro.getAutorNombre(), 15),
                            truncarTexto(libro.getGenero(), 10),
                            libro.getPrecio(),
                            libro.getStock());
                }

                totalLibrosProcesados += librosPagina.size();

                System.out.println();
                System.out.printf("Libros en esta página: %d%n", librosPagina.size());
                System.out.printf("Total procesado hasta ahora: %d%n", totalLibrosProcesados);

                // Si la página actual tiene menos elementos que el tamaño de página,
                // es la última página
                if (librosPagina.size() < TAMANO_PAGINA) {
                    System.out.println("\n*** Esta es la última página ***");
                    break;
                }

                // Preparar para siguiente página
                paginaActual++;
                offset += TAMANO_PAGINA;

                System.out.println();
            }

            // Resumen final
            System.out.println("\n" + "=".repeat(50));
            System.out.println("RESUMEN DE PAGINACIÓN");
            System.out.println("=".repeat(50));
            System.out.printf("Total de páginas procesadas: %d%n", paginaActual);
            System.out.printf("Tamaño de página: %d%n", TAMANO_PAGINA);
            System.out.printf("Total de libros procesados: %d%n", totalLibrosProcesados);

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