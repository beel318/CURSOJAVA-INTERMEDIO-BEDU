import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Crear tareas de profesionales médicos
        Runnable draSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfermeroLopez = () -> salaCirugia.usar("Enfermero López");
        Runnable draMartinez = () -> salaCirugia.usar("Dra. Martínez");
        Runnable drRuiz = () -> salaCirugia.usar("Dr. Ruiz");

        // Crear pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Ejecutar las tareas
        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfermeroLopez);
        executor.execute(draMartinez);
        executor.execute(drRuiz);

        executor.shutdown(); // No acepta más tareas
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS); // Esperar finalización
        } catch (InterruptedException e) {
            System.out.println("Error al esperar finalización del executor");
        }

        System.out.println("Simulación finalizada.");
    }
}