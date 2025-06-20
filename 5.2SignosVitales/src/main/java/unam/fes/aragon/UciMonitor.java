package unam.fes.aragon;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

public class UciMonitor {

    static Random random = new Random();

    static class SignosVitales {
        int pacienteId;
        int frecuenciaCardiaca;
        int presionSistolica;
        int presionDiastolica;
        int oxigeno;

        public SignosVitales(int id) {
            this.pacienteId = id;
            this.frecuenciaCardiaca = 40 + random.nextInt(100); // 40–140
            this.presionSistolica = 80 + random.nextInt(80);     // 80–160
            this.presionDiastolica = 50 + random.nextInt(40);    // 50–90
            this.oxigeno = 85 + random.nextInt(15);              // 85–100
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Flux<SignosVitales> paciente1 = generarPaciente(1);
        Flux<SignosVitales> paciente2 = generarPaciente(2);
        Flux<SignosVitales> paciente3 = generarPaciente(3);

        Flux<SignosVitales> flujoUCI = Flux.merge(paciente1, paciente2, paciente3);

        flujoUCI
                .filter(UciMonitor::esCritico)
                .delayElements(Duration.ofSeconds(1)) // backpressure artificial
                .publishOn(Schedulers.parallel())
                .subscribe(UciMonitor::mostrarAlerta);

        Thread.sleep(20000); // 20 segundos de simulación
    }

    // Simula flujo continuo para un paciente
    public static Flux<SignosVitales> generarPaciente(int id) {
        return Flux.interval(Duration.ofMillis(300))
                .onBackpressureBuffer() //
                .map(i -> new SignosVitales(id));
    }

    // Detecta si hay alguna anomalía
    public static boolean esCritico(SignosVitales s) {
        return s.frecuenciaCardiaca < 50 || s.frecuenciaCardiaca > 120 ||
                s.presionSistolica < 90 || s.presionSistolica > 140 ||
                s.presionDiastolica < 60 || s.presionDiastolica > 90 ||
                s.oxigeno < 90;
    }

    // Prioriza FC > SpO2 > PA en impresión
    public static void mostrarAlerta(SignosVitales s) {
        if (s.frecuenciaCardiaca < 50 || s.frecuenciaCardiaca > 120) {
            System.out.println("Paciente " + s.pacienteId + " - FC crítica: " + s.frecuenciaCardiaca + " bpm");
        } else if (s.oxigeno < 90) {
            System.out.println("Paciente " + s.pacienteId + " - SpO2 baja: " + s.oxigeno + "%");
        } else {
            System.out.println("Paciente " + s.pacienteId + " - PA crítica: " +
                    s.presionSistolica + "/" + s.presionDiastolica + " mmHg");
        }
    }
}
