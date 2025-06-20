package com.bedu.inventario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Producto {

    @Id // Campo que funcionará como clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se generará automáticamente (autoincremental)
    private Long id;

    // Campos que serán columnas en la tabla 'producto'
    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Min(1)
    private double precio;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    protected Producto() {} // Constructor por defecto requerido por JPA

    public Producto(String nombre, String descripcion, double precio, Marca marca) {
        this.marca = marca;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    // Getters para acceder a los atributos (necesarios para el funcionamiento de JPA y buenas prácticas)
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    public Marca getMarca() {
        return marca;
    }

    // Dentro del metodo toString(), agrega la categoría de forma opcional
    @Override
    public String toString() {
        return String.format(
                "Producto[id=%d, nombre='%s', precio=%.2f, categoria='%s']",
                id, nombre, precio, marca
        );
    }
}