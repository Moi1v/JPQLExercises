package com.darwinruiz.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@NamedQuery(
        name = "Cliente.buscarPorEmail",
        query = "SELECT cliente FROM Cliente cliente WHERE cliente.email = :email"
)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String nombre;

    @Column(length = 150, unique = true)
    private String email;

    @Column(length = 80)
    private String ciudad;

    private Integer edad;

    private Boolean activo = Boolean.TRUE;

    private LocalDate fechaRegistro = LocalDate.now();

    // Getters y setters
    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Cliente{id=%d, nombre='%s', email='%s', ciudad='%s', edad=%s, activo=%s, fechaRegistro=%s}"
                .formatted(id, nombre, email, ciudad, edad, activo, fechaRegistro);
    }
}