package com.alesandro.instagramhibernate.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String nombre;
    private double edad;
    @ManyToMany(mappedBy = "usuarios", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Contenido> contenidos = new HashSet<Contenido>();

    public Usuario(String email, String nombre, double edad) {
        this.email = email;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Usuario() {}

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    public Set<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(Set<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public void setContenido(Contenido contenido) {
        contenidos.add(contenido);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id_usuario == usuario.id_usuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_usuario);
    }

}
