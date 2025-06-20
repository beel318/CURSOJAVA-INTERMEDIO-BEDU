package com.bedu.rrhhapi.service;

import com.bedu.rrhhapi.model.Empleado;
import com.bedu.rrhhapi.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<Empleado> obtenerTodos() {
        return repository.findAll();
    }

    public List<Empleado> buscarPorPuesto(String puesto) {
        return repository.findByPuestoIgnoreCase(puesto);
    }

    public void eliminarEmpleado(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("No existe un empleado con ID " + id);
        }
        repository.deleteById(id);
    }
}
