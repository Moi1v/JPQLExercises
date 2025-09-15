package com.darwinruiz.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 180)
    private String titulo;

    @Column(length = 120)
    private String autorNombre; // sin asociación; solo texto

    @Column(length = 60)
    private String genero;

    @Column(precision = 12, scale = 2)
    private BigDecimal precio = BigDecimal.ZERO;

    private Integer stock = 0;

    private Boolean activo = Boolean.TRUE;

    private LocalDate fechaPublicacion = LocalDate.now();

    @Column(length = 250)
    private String descripcion; // puede ser NULL para ejercicios de COALESCE

    // Getters y setters
    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Libro{id=%d, titulo='%s', autor='%s', genero='%s', precio=%s, stock=%s, activo=%s, fechaPublicacion=%s}"
                .formatted(id, titulo, autorNombre, genero, precio, stock, activo, fechaPublicacion);
    }
}