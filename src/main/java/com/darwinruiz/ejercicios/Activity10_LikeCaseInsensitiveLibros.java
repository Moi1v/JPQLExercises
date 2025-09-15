package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
Trae los libros cuyo título termine en 'o' (insensible a mayúsculas)
y cuyo género empiece con 'fic' (ej. 'Ficción').
Ordena por titulo ASC.
*/
public class Activity10_LikeCaseInsensitiveLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Libro> query = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE :fin AND LOWER(l.genero) LIKE :inicio ORDER BY l.titulo ASC",
                    Libro.class);

            query.setParameter("fin", "%o"); // Títulos que terminan en 'o'
            query.setParameter("inicio", "fic%"); // Géneros que empiezan con 'fic'

            List<Libro> libros = query.getResultList();

            System.out.println("=== LIBROS CON TÍTULO QUE TERMINA EN 'O' Y GÉNERO QUE EMPIEZA CON 'FIC' ===");
            System.out.println("Criterios de búsqueda:");
            System.out.println("- Título termina en 'o' (case-insensitive)");
            System.out.println("- Género empieza con 'fic' (case-insensitive)");
            System.out.println("- Ordenados por título ASC");
            System.out.println();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros que cumplan ambos criterios.");
            } else {
                System.out.println("Total de libros encontrados: " + libros.size());
                System.out.println();

                for (Libro libro : libros) {
                    System.out.printf("ID: %d | Título: %s | Género: %s | Precio: $%.2f | Stock: %d%n",
                            libro.getId(),
                            libro.getTitulo(),
                            libro.getGenero(),
                            libro.getPrecio(),
                            libro.getStock());
                }
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}