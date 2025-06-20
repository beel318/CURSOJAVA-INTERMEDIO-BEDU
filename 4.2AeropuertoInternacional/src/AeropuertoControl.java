import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    private static final Random random = new Random();

    private static CompletableFuture<Boolean> simularVerificacion(String servicio, int probabilidad, int delaySegundos) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(delaySegundos);
                boolean exito = random.nextInt(100) < probabilidad;
                System.out.println(servicio + ": " + exito);
                return exito;
            } catch (InterruptedException e) {
                throw new RuntimeException(servicio + " interrumpido.");
            }
        });
    }

    public CompletableFuture<Boolean> verificarPista() {
        return simularVerificacion("Pista disponible", 80, 2);
    }

    public CompletableFuture<Boolean> verificarClima() {
        return simularVerificacion("Clima favorable", 85, 2);
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return simularVerificacion("Tráfico aéreo despejado", 90, 3);
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return simularVerificacion("Personal disponible", 95, 2);
    }
}
