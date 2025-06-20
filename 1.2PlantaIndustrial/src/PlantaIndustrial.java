import java.util.ArrayList;
import java.util.List;

public class PlantaIndustrial {
    //Metodo para mostrar ordenes, recorre la lista
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    //Metodo para mostrar las órdenes con costo adicional
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                ((OrdenPersonalizada) obj).aplicarCostoAdicional(costoAdicional);
            }
        }
    }

    //Metodo para contar las órdenes
    public static void contarOrdenes(List<OrdenProduccion> lista) {
        int masa = 0, personalizadas = 0, prototipos = 0;
        for (OrdenProduccion orden : lista) {
            if (orden instanceof OrdenMasa) masa++;
            else if (orden instanceof OrdenPersonalizada) personalizadas++;
            else if (orden instanceof OrdenPrototipo) prototipos++;
        }
        System.out.println("\nResumen total de órdenes:");
        System.out.println("Producción en masa: " + masa);
        System.out.println("Personalizadas: " + personalizadas);
        System.out.println("Prototipos: " + prototipos);
    }

    public static void main(String[] args) {
        //Crear objetos de lista
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        List<OrdenProduccion> todas = new ArrayList<>();

        // Crear órdenes
        OrdenMasa om1 = new OrdenMasa("A123", 500);
        OrdenMasa om2 = new OrdenMasa("A124", 750);
        ordenesMasa.add(om1);
        ordenesMasa.add(om2);

        //Crear órdenes personalizadas
        OrdenPersonalizada op1 = new OrdenPersonalizada("P456", 100, "ClienteX");
        OrdenPersonalizada op2 = new OrdenPersonalizada("P789", 150, "ClienteY");
        ordenesPersonalizadas.add(op1);
        ordenesPersonalizadas.add(op2);

        //Crear órdenes prototipo
        OrdenPrototipo pt1 = new OrdenPrototipo("T789", 10, "Diseño");
        OrdenPrototipo pt2 = new OrdenPrototipo("T790", 5, "Pruebas");
        ordenesPrototipo.add(pt1);
        ordenesPrototipo.add(pt2);

        // Mostrar órdenes
        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesMasa);

        System.out.println("\nÓrdenes registradas:");
        mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("\nÓrdenes registradas:");
        mostrarOrdenes(ordenesPrototipo);

        // Procesar personalizadas
        System.out.println("\nProcesando órdenes personalizadas...");
        procesarPersonalizadas(ordenesPersonalizadas, 200);

        // Añadir todas a una sola lista para el conteo
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);

        contarOrdenes(todas);
    }
}