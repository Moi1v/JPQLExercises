package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
ENUNCIADO:
Consulta clientes cuya ciudad esté en una lista (IN :ciudades),
por ejemplo: ["Guatemala","Antigua","Cobán"].
Imprime resultados.
*/
public class Activity14_IN_ListaDeCiudadesClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<String> ciudades = Arrays.asList("Guatemala", "Antigua", "Cobán");

            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE c.ciudad IN :ciudades ORDER BY c.ciudad, c.nombre",
                    Cliente.class);

            query.setParameter("ciudades", ciudades);

            List<Cliente> clientes = query.getResultList();

            System.out.println("=== CLIENTES POR CIUDADES ESPECÍFICAS ===");
            System.out.println("Ciudades buscadas: " + ciudades);
            System.out.println("Ordenados por ciudad y luego por nombre");
            System.out.println();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes en las ciudades especificadas.");
            } else {
                System.out.println("Total de clientes encontrados: " + clientes.size());
                System.out.println();
                System.out.println("ID | Nombre | Email | Ciudad | Edad | Activo | Fecha Registro");
                System.out.println("=".repeat(100));

                for (Cliente cliente : clientes) {
                    System.out.printf("%3d | %-20s | %-25s | %-15s | %3d | %-6s | %s%n",
                            cliente.getId(),
                            truncarTexto(cliente.getNombre(), 20),
                            truncarTexto(cliente.getEmail(), 25),
                            cliente.getCiudad(),
                            cliente.getEdad(),
                            cliente.getActivo() ? "Sí" : "No",
                            cliente.getFechaRegistro());
                }

                // Estadísticas por ciudad
                System.out.println("\n=== RESUMEN POR CIUDAD ===");
                Map<String, Long> clientesPorCiudad = clientes.stream()
                        .collect(Collectors.groupingBy(Cliente::getCiudad, Collectors.counting()));

                clientesPorCiudad.entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .forEach(entry -> {
                            System.out.printf("%-15s: %2d clientes%n", entry.getKey(), entry.getValue());
                        });

                // Estadísticas adicionales
                System.out.println("\n=== ESTADÍSTICAS ADICIONALES ===");
                long clientesActivos = clientes.stream().filter(Cliente::getActivo).count();
                long clientesInactivos = clientes.size() - clientesActivos;

                System.out.println("Clientes activos: " + clientesActivos);
                System.out.println("Clientes inactivos: " + clientesInactivos);

                double edadPromedio = clientes.stream()
                        .mapToInt(Cliente::getEdad)
                        .average()
                        .orElse(0.0);

                System.out.printf("Edad promedio: %.1f años%n", edadPromedio);
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