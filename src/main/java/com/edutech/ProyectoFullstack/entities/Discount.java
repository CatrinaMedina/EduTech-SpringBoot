package com.edutech.ProyectoFullstack.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private int porcentaje;
    private String fechainicio;
    private String fechafin;

    public Discount() {
    }

    public Discount(Long id, String codigo, int porcentaje, String fechainicio, String fechafin) {
        this.id = id;
        this.codigo = codigo;
        this.porcentaje = porcentaje;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public String toString() {
        return "Discount [id=" + id + ", codigo=" + codigo + ", porcentaje=" + porcentaje + ", fechainicio="
                + fechainicio + ", fechafin=" + fechafin + "]";
    }

}
