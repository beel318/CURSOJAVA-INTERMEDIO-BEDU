package com.bedu.rrhhapi.repository;

import com.bedu.rrhhapi.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByPuestoIgnoreCase(String puesto);
}

