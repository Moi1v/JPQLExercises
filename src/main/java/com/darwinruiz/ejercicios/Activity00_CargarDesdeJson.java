package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
ENUNCIADO:
Cargar TODOS los datos desde JSON (resources/data/clientes.json y libros.json) e insertarlos en la base.
- Sin argumentos: siempre inserta todos los registros que estén en los JSON.
- Usa lotes de 50 para flush/clear y evitar consumo de memoria.
IMPORTANTE:
- Si ejecutas varias veces con JSONs que repiten correos, el unique constraint de Cliente.email puede fallar.
*/
public class Activity00_CargarDesdeJson {

    private static final String PERSISTENCE_UNIT = "JPQLExercisesPU";
    private static final String CLIENTES_PATH = "data/clientes.json";
    private static final String LIBROS_PATH = "data/libros.json";
    private static final int TAMANO_LOTE = 50;

    public static void main(String[] args) throws Exception {
        List<ClienteJson> listaClientesJson = leerClientesJson();
        List<LibroJson> listaLibrosJson = leerLibrosJson();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            int contador = 0;
            for (ClienteJson clienteJson : listaClientesJson) {
                Cliente cliente = new Cliente();
                cliente.setNombre(clienteJson.getNombre());
                cliente.setEmail(clienteJson.getEmail());
                cliente.setCiudad(clienteJson.getCiudad());
                cliente.setEdad(clienteJson.getEdad());
                cliente.setActivo(clienteJson.getActivo() != null ? clienteJson.getActivo() : Boolean.TRUE);
                cliente.setFechaRegistro(
                        clienteJson.getFechaRegistro() != null ? clienteJson.getFechaRegistro() : LocalDate.now()
                );
                entityManager.persist(cliente);

                contador++;
                if (contador % TAMANO_LOTE == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            System.out.println("Clientes insertados desde JSON: " + listaClientesJson.size());

            contador = 0;
            for (LibroJson libroJson : listaLibrosJson) {
                Libro libro = new Libro();
                libro.setTitulo(libroJson.getTitulo());
                libro.setAutorNombre(libroJson.getAutorNombre());
                libro.setGenero(libroJson.getGenero());
                libro.setPrecio(libroJson.getPrecio() != null ? libroJson.getPrecio() : new BigDecimal("0.00"));
                libro.setStock(libroJson.getStock() != null ? libroJson.getStock() : 0);
                libro.setActivo(libroJson.getActivo() != null ? libroJson.getActivo() : Boolean.TRUE);
                libro.setFechaPublicacion(
                        libroJson.getFechaPublicacion() != null ? libroJson.getFechaPublicacion() : LocalDate.now()
                );
                libro.setDescripcion(libroJson.getDescripcion());
                entityManager.persist(libro);

                contador++;
                if (contador % TAMANO_LOTE == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            System.out.println("Libros insertados desde JSON: " + listaLibrosJson.size());

            entityManager.getTransaction().commit();
            System.out.println("Carga desde JSON completada correctamente.");
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    // ---------- Lectura JSON ----------

    private static List<ClienteJson> leerClientesJson() throws Exception {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(CLIENTES_PATH);
        if (inputStream == null) {
            throw new IllegalStateException("No se encontró el archivo: " + CLIENTES_PATH);
        }
        ObjectMapper objectMapper = crearMapper();
        return objectMapper.readValue(
                inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ClienteJson.class)
        );
    }

    private static List<LibroJson> leerLibrosJson() throws Exception {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(LIBROS_PATH);
        if (inputStream == null) {
            throw new IllegalStateException("No se encontró el archivo: " + LIBROS_PATH);
        }
        ObjectMapper objectMapper = crearMapper();
        return objectMapper.readValue(
                inputStream,
                objectMapper.getTypeFactory().constructCollectionType(List.class, LibroJson.class)
        );
    }

    private static ObjectMapper crearMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // LocalDate
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    // ---------- POJOs JSON (tipos explícitos) ----------

    public static class ClienteJson {
        private String nombre;
        private String email;
        private String ciudad;
        private Integer edad;
        private Boolean activo;
        private LocalDate fechaRegistro;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public Integer getEdad() {
            return edad;
        }

        public void setEdad(Integer edad) {
            this.edad = edad;
        }

        public Boolean getActivo() {
            return activo;
        }

        public void setActivo(Boolean activo) {
            this.activo = activo;
        }

        public LocalDate getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(LocalDate fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }
    }

    public static class LibroJson {
        private String titulo;
        private String autorNombre;
        private String genero;
        private BigDecimal precio;
        private Integer stock;
        private Boolean activo;
        private LocalDate fechaPublicacion;
        private String descripcion;

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutorNombre() {
            return autorNombre;
        }

        public void setAutorNombre(String autorNombre) {
            this.autorNombre = autorNombre;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public BigDecimal getPrecio() {
            return precio;
        }

        public void setPrecio(BigDecimal precio) {
            this.precio = precio;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public Boolean getActivo() {
            return activo;
        }

        public void setActivo(Boolean activo) {
            this.activo = activo;
        }

        public LocalDate getFechaPublicacion() {
            return fechaPublicacion;
        }

        public void setFechaPublicacion(LocalDate fechaPublicacion) {
            this.fechaPublicacion = fechaPublicacion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }
}
