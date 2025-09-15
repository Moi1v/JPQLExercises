package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para encontrar un cliente por email
y actualizar su nombre y ciudad.
Restricciones:
- Maneja transacción.
- Si no encuentra, muestra un mensaje.
*/
public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            String emailBuscar = "maria.gonzalez@ejmplo.com";

            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscar)
                        .getSingleResult();

                System.out.println("Cliente encontrado: " + cliente);

                // Actualizar datos
                cliente.setNombre("María González");
                cliente.setCiudad("Ciudad de Guatemala, San Jose Pinula");

                entityManager.merge(cliente);

                System.out.println("Cliente actualizado: " + cliente);

            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con el email: " + emailBuscar);
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