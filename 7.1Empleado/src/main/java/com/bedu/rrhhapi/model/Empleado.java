package com.bedu.rrhhapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Empleado {
    @Id
    private Long id;
    private String nombre;
    private String puesto;
    private double salario;

    public Empleado(Long id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Empleado() {

    }

    // Getters y setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPuesto() { return puesto; }
    public double getSalario() { return salario; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public void setSalario(double salario) { this.salario = salario; }
}