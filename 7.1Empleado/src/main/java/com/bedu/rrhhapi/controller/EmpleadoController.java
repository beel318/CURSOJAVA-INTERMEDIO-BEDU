package com.bedu.rrhhapi.controller;

import com.bedu.rrhhapi.model.Empleado;
import com.bedu.rrhhapi.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Empleado> ListarEmpleados() {
        return service.obtenerTodos();
    }

    @GetMapping("/puesto/{puesto}")
    public List<Empleado> buscarPorPuesto(@PathVariable String puesto) {
        return service.buscarPorPuesto(puesto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        try {
            service.eliminarEmpleado(id);
            return ResponseEntity.ok("Empleado con ID " + id + " eliminado correctamente.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }
}
