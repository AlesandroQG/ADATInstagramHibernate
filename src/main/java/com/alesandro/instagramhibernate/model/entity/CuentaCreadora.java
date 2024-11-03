package com.alesandro.instagramhibernate.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class CuentaCreadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, unique = true, nullable = false)
    private String cuenta;
    private double anio;
    @OneToMany(mappedBy = "id_contenido")
    private Set<Contenido> contenidos = new HashSet<Contenido>();

    public CuentaCreadora(String cuenta, double anio) {
        this.cuenta = cuenta;
        this.anio = anio;
    }

    public CuentaCreadora() {}

    @Override
    public String toString() {
        return "CuentaCreadora{" +
                "id=" + id +
                ", cuenta='" + cuenta + '\'' +
                ", anio=" + anio +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getAnio() {
        return anio;
    }

    public void setAnio(double anio) {
        this.anio = anio;
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
        CuentaCreadora that = (CuentaCreadora) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
