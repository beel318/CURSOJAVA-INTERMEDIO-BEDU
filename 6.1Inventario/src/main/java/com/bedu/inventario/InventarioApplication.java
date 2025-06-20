package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            // Guardar al menos 4 productos
            repository.save(new Producto("Laptop Lenovo", "Portátil 15.6 pulgadas", 12500.00));
            repository.save(new Producto("Mouse Logitech", "Ergonómico y rápido", 350.00));
            repository.save(new Producto("Teclado Mecánico", "Switch azul", 950.00));
            repository.save(new Producto("Monitor", "27 pulgadas Full HD", 3200.00));

            System.out.println("Todos los productos:");
            repository.findAll().forEach(System.out::println);

            // Productos con precio > 500
            System.out.println("\nProductos con precio mayor a 500:");
            repository.findByPrecioGreaterThan(500)
                    .forEach(System.out::println);

            // Productos que contienen "lap"
            System.out.println("\nProductos que contienen 'lap':");
            repository.findByNombreContainingIgnoreCase("lap")
                    .forEach(System.out::println);

            // Productos con precio entre 400 y 1000
            System.out.println("\nProductos con precio entre 400 y 1000:");
            repository.findByPrecioBetween(400, 1000)
                    .forEach(System.out::println);

            // Productos cuyo nombre empieza con "m" o "M"
            System.out.println("\nProductos cuyo nombre empieza con 'm':");
            repository.findByNombreStartingWithIgnoreCase("m")
                    .forEach(System.out::println);
        };
    }
}
