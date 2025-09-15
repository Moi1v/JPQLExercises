package com.darwinruiz.dto;

import java.math.BigDecimal;

public class LibroResumenDto {
    private final Long id;
    private final String titulo;
    private final BigDecimal precio;

    public LibroResumenDto(Long id, String titulo, BigDecimal precio) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "%d | %s | %s".formatted(id, titulo, precio);
    }
}