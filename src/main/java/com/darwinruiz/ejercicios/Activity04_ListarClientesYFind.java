package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
1) Lista todos los clientes ordenados por nombre (JPQL).
2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
3) Imprime ambos resultados.
*/
public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // 1) Listar todos los clientes ordenados por nombre
            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c ORDER BY c.nombre", Cliente.class);
            List<Cliente> listaClientes = query.getResultList();

            System.out.println("=== LISTA DE TODOS LOS CLIENTES ORDENADOS POR NOMBRE ===");
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }

            // 2) Tomar el primero y buscarlo con find()
            if (!listaClientes.isEmpty()) {
                Cliente primerCliente = listaClientes.get(0);
                Long idPrimerCliente = primerCliente.getId();

                System.out.println("\n=== PRIMER CLIENTE DE LA LISTA ===");
                System.out.println("Primer cliente: " + primerCliente);

                // Buscar por ID usando find()
                Cliente clienteEncontrado = entityManager.find(Cliente.class, idPrimerCliente);

                System.out.println("\n=== CLIENTE ENCONTRADO CON FIND() ===");
                System.out.println("Cliente encontrado por ID " + idPrimerCliente + ": " + clienteEncontrado);

            } else {
                System.out.println("No hay clientes en la base de datos.");
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}