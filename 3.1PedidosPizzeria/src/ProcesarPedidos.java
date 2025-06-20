import java.util.List;
import java.util.Optional;

public class ProcesarPedidos {
    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("Juan", "domicilio", "555-1234"),
                new Pedido("Ana", "local", "555-1111"),
                new Pedido("Carlos", "domicilio", null),
                new Pedido("Lucía", "domicilio", "555-5678"),
                new Pedido("Elena", "local", null)
        );

        System.out.println("Procesando confirmaciones de pedidos a domicilio...\n");

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(tel -> "Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}
