package com.darwinruiz.ejercicios;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
            // TODO: SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE :fin AND LOWER(l.genero) LIKE :inicio ORDER BY l.titulo
            // TODO: imprimir resultados
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}