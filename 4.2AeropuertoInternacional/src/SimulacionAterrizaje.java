import java.util.concurrent.CompletableFuture;

public class SimulacionAterrizaje {
    public static void main(String[] args) {
        System.out.println("Verificando condiciones para aterrizaje...\n");

        AeropuertoControl control = new AeropuertoControl();

        CompletableFuture<Boolean> pista = control.verificarPista()
                .exceptionally(e -> {
                    System.out.println("Error en pista: " + e.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> clima = control.verificarClima()
                .exceptionally(e -> {
                    System.out.println("Error en clima: " + e.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> trafico = control.verificarTraficoAereo()
                .exceptionally(e -> {
                    System.out.println("Error en tráfico aéreo: " + e.getMessage());
                    return false;
                });

        CompletableFuture<Boolean> personal = control.verificarPersonalTierra()
                .exceptionally(e -> {
                    System.out.println("Error en personal de tierra: " + e.getMessage());
                    return false;
                });

        // Combinar todos los resultados
        CompletableFuture<Void> resultadoFinal = CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenRun(() -> {
                    try {
                        boolean autorizado =
                                pista.get() &&
                                        clima.get() &&
                                        trafico.get() &&
                                        personal.get();

                        System.out.println();
                        if (autorizado) {
                            System.out.println("Aterrizaje autorizado: todas las condiciones óptimas.");
                        } else {
                            System.out.println("Aterrizaje denegado: condiciones no óptimas.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al combinar resultados: " + e.getMessage());
                    }
                });

        // Esperar finalización del proceso asincrónico
        resultadoFinal.join();
    }
}
