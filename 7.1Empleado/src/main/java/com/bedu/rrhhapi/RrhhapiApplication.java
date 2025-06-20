package com.bedu.rrhhapi;
import com.bedu.rrhhapi.model.Empleado;
import com.bedu.rrhhapi.repository.EmpleadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RrhhapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RrhhapiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(EmpleadoRepository repo) {
		return args -> {
			// Carga de datos inicial
			repo.save(new Empleado(1L,"Ana Gómez", "Gerente de Marketing", 55000));
			repo.save(new Empleado(2L,"Carlos Pérez", "Desarrollador Backend", 45000));
			repo.save(new Empleado(3L,"Lucía Ríos", "Desarrollador Frontend", 43000));
			repo.save(new Empleado(4L,"Miguel Hernández", "Diseñador UI/UX", 42000));
		};
	}
}