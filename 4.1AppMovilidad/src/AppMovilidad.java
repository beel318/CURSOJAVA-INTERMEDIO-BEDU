import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AppMovilidad {

    // Simula el cálculo de ruta con latencia de 2-3 segundos
    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Calculando ruta...");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2, 4));
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error durante el cálculo de ruta", e);
            }
        });
    }

    // Simula la estimación de tarifa con latencia de 1-2 segundos
    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Estimando tarifa...");
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error durante la estimación de tarifa", e);
            }
        });
    }

    // Metodo para iniciar el proceso y combinar los resultados
    public void procesarSolicitud() {
        CompletableFuture<String> rutaFuture = calcularRuta()
                .exceptionally(ex -> {
                    System.out.println("Error al calcular ruta: " + ex.getMessage());
                    return "Ruta no disponible";
                });

        CompletableFuture<Double> tarifaFuture = estimarTarifa()
                .exceptionally(ex -> {
                    System.out.println("Error al estimar tarifa: " + ex.getMessage());
                    return -1.0;
                });

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) -> {
            if (tarifa < 0) {
                return "No se pudo estimar la tarifa.";
            }
            return "Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa;
        }).thenAccept(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        AppMovilidad app = new AppMovilidad();
        app.procesarSolicitud();

        // Para evitar que el programa termine antes de ver el resultado
        TimeUnit.SECONDS.sleep(5);
    }
}
