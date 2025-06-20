import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MisionEspacial {
    public static void main(String[] args) {
        System.out.println("Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviar tareas
        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> vida = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comms = executor.submit(new SistemaComunicaciones());

        try {
            // Mostrar resultados
            System.out.println(comms.get());
            System.out.println(vida.get());
            System.out.println(termico.get());
            System.out.println(nav.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error en la ejecución de los sistemas: " + e.getMessage());
        } finally {
            executor.shutdown();
            System.out.println("Todos los sistemas reportan estado operativo.");
        }
    }
}