package com.alesandro.instagramhibernate.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contenido;
    @Column(length = 100, unique = true, nullable = false)
    private String nombre;
    @Column(length = 20)
    private String tag;
    private double tiempo;
    @Column(length = 100)
    private String ubicacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private CuentaCreadora cuentaCreadora;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "recomendacion",
    joinColumns = @JoinColumn(name = "id_contenido", referencedColumnName = "id_contenido"),
    inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"))
    private Set<Usuario> usuarios = new HashSet<Usuario>();

    public Contenido(String nombre, String tag, double tiempo, String ubicacion, CuentaCreadora cuentaCreadora) {
        this.nombre = nombre;
        this.tag = tag;
        this.tiempo = tiempo;
        this.ubicacion = ubicacion;
        this.cuentaCreadora = cuentaCreadora;
    }

    public Contenido() {}

    @Override
    public String toString() {
        return "Contenido{" +
                "id_contenido=" + id_contenido +
                ", nombre='" + nombre + '\'' +
                ", tag='" + tag + '\'' +
                ", tiempo=" + tiempo +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }

    public int getId_contenido() {
        return id_contenido;
    }

    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public CuentaCreadora getCuentaCreadora() {
        return cuentaCreadora;
    }

    public void setCuentaCreadora(CuentaCreadora cuentaCreadora) {
        this.cuentaCreadora = cuentaCreadora;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contenido contenido = (Contenido) o;
        return id_contenido == contenido.id_contenido;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_contenido);
    }

}
