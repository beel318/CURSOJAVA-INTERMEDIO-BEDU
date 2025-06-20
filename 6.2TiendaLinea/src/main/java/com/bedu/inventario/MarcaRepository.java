package com.bedu.inventario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findByNombreContaining(String nombre);
    List<Marca> findByNombreContainingIgnoreCase(String nombre);
    List<Marca> findByNombreStartingWithIgnoreCase(String prefijo);
}