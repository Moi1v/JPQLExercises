package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para buscar un cliente por correo.
Imprime el resultado o un mensaje si no existe.
*/
public class Activity08_BuscarClienteNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String emailBuscar = "maria.gonzalez@ejmplo.com";

            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscar)
                        .getSingleResult();

                System.out.println("=== CLIENTE ENCONTRADO CON NAMED QUERY ===");
                System.out.println("Email buscado: " + emailBuscar);
                System.out.println("Cliente encontrado: " + cliente);

            } catch (NoResultException e) {
                System.out.println("=== CLIENTE NO ENCONTRADO ===");
                System.out.println("No se encontró ningún cliente con el email: " + emailBuscar);
                System.out.println("Verifique que el email exista en la base de datos.");
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}