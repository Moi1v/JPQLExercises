package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
Consulta clientes cuyo nombre contenga 'a' (case-insensitive),
ordena por ciudad ASC, y devuelve la página 2 (tamaño 3).
Imprime los resultados.
*/
public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Crear query con filtro case-insensitive y ordenamiento
            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE :patron ORDER BY c.ciudad ASC",
                    Cliente.class);

            query.setParameter("patron", "%a%"); // Contiene 'a' (case-insensitive debido a LOWER)

            // Paginación: página 2 con tamaño 3
            // Página 1: registros 0-2, Página 2: registros 3-5
            query.setFirstResult(3);  // Saltar los primeros 3 (página 1)
            query.setMaxResults(3);   // Máximo 3 resultados

            List<Cliente> clientesPagina2 = query.getResultList();

            System.out.println("=== CLIENTES QUE CONTIENEN 'a' EN EL NOMBRE (PÁGINA 2, TAMAÑO 3) ===");
            System.out.println("Ordenados por ciudad ASC");
            System.out.println("Total de resultados en esta página: " + clientesPagina2.size());
            System.out.println();

            for (Cliente cliente : clientesPagina2) {
                System.out.println(cliente);
            }

            if (clientesPagina2.isEmpty()) {
                System.out.println("No hay más resultados en la página 2.");
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}