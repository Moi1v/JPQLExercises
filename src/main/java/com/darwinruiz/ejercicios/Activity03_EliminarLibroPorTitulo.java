package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/*
ENUNCIADO:
Elimina un libro buscándolo por título exacto con JPQL.
Restricciones:
- Usa getSingleResult() o maneja lista vacía.
- Transacción obligatoria.
*/
public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            String tituloBuscar = "1984";

            try {
                TypedQuery<Libro> query = entityManager.createQuery(
                        "SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class);
                query.setParameter("titulo", tituloBuscar);

                Libro libro = query.getSingleResult();

                System.out.println("Libro encontrado para eliminar: " + libro);

                entityManager.remove(libro);

                System.out.println("Libro eliminado exitosamente: " + tituloBuscar);

            } catch (NoResultException e) {
                System.out.println("No se encontró ningún libro con el título: " + tituloBuscar);
            }

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