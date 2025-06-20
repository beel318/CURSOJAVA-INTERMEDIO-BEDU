import java.util.List;
import java.util.stream.Stream;

public class ProcesadorCalidad {
    public static void main(String[] args) {
        // Encuestas por sucursal
        List<Encuesta> centro = List.of(
                new Encuesta("Pedro", "El tiempo de espera fue largo", 2),
                new Encuesta("Laura", null, 4),
                new Encuesta("Ana", null, 3)
        );

        List<Encuesta> norte = List.of(
                new Encuesta("Luis", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Marta", "Todo excelente", 5)
        );

        List<Sucursal> sucursales = List.of(
                new Sucursal("Centro", centro),
                new Sucursal("Norte", norte)
        );

        // Procesamiento
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3)
                                .flatMap(e -> e.getComentario()
                                        .map(comentario -> Stream.of(
                                                "Sucursal " + sucursal.getNombre() +
                                                        ": Seguimiento a paciente con comentario: \"" + comentario + "\""
                                        ))
                                        .orElseGet(Stream::empty)
                                )
                )
                .forEach(System.out::println);
    }
}
