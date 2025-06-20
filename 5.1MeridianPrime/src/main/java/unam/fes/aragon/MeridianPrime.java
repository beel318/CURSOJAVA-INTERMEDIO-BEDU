package unam.fes.aragon;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrime {

    static Random random = new Random();
    static AtomicInteger semaforoRojoRepetido = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101))
                .onBackpressureBuffer()
                .filter(nivel -> nivel > 70)
                .map(nivel -> "Alerta: Congestión del " + nivel + "% en Avenida Solar");

        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(101))
                .filter(pm -> pm > 50)
                .map(pm -> "Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");

        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    return prioridades[random.nextInt(3)];
                })
                .filter(p -> p.equals("Alta"))
                .map(p -> "Emergencia vial: Accidente con prioridad " + p);

        Flux<String> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11))
                .onBackpressureBuffer()
                .filter(min -> min > 5)
                .map(min -> "Tren maglev con retraso crítico: " + min + " minutos");

        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(3)];
                })
                .filter(estado -> {
                    if (estado.equals("Rojo")) {
                        return semaforoRojoRepetido.incrementAndGet() >= 3;
                    } else {
                        semaforoRojoRepetido.set(0);
                        return false;
                    }
                })
                .map(estado -> "Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");

        // Unimos todos los flujos críticos
        Flux<String> eventosCriticos = Flux.merge(
                trafico.subscribeOn(Schedulers.parallel()),
                contaminacion.subscribeOn(Schedulers.parallel()),
                accidentes.subscribeOn(Schedulers.parallel()),
                trenes.subscribeOn(Schedulers.parallel()),
                semaforos.subscribeOn(Schedulers.parallel())
        );

        // Emitir eventos
        eventosCriticos
                .doOnNext(System.out::println)
                .buffer(Duration.ofSeconds(1)) // cada segundo evaluamos los eventos agrupados
                .filter(lista -> lista.size() >= 3)
                .map(lista -> "Alerta global: Múltiples eventos críticos detectados en Meridian Prime\n" +
                        String.join("\n", lista))
                .subscribe(System.out::println);

        // Para mantener la app corriendo
        Thread.sleep(20000); // 20 segundos de simulación
    }
}
