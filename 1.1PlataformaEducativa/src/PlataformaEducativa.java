import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class PlataformaEducativa {

    // Metodo para mostrar materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Metodo para contar duración de videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracionMin();
        }
        System.out.println("\n Duración total de videos: " + total + " minutos");
    }

    // Metodo para marcar ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                ((Ejercicio) obj).marcarRevisado();
            }
        }
    }

    // Filtrar por autor
    public static <T extends MaterialCurso> void filtrarPorAutor(List<T> lista, Predicate<T> criterio) {
        System.out.println("\n Filtrando materiales por autor:");
        for (T material : lista) {
            if (criterio.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();

        // Agregar videos
        Video v1 = new Video("Introducción a Java", "Mario", 15);
        Video v2 = new Video("POO en Java", "Carlos", 20);

        // Agregar artículos
        Articulo a1 = new Articulo("Historia de Java", "Ana", 1200);
        Articulo a2 = new Articulo("Tipos de datos", "Luis", 800);

        // Agregar ejercicios
        Ejercicio e1 = new Ejercicio("Variables y tipos", "Luis");
        Ejercicio e2 = new Ejercicio("Condicionales", "Mario");

        // Añadir a la lista general
        materiales.add(v1);
        materiales.add(v2);
        materiales.add(a1);
        materiales.add(a2);
        materiales.add(e1);
        materiales.add(e2);

        System.out.println("Materiales del curso:");
        mostrarMateriales(materiales);

        // Crear lista de videos y contar duración
        List<Video> videos = Arrays.asList(v1, v2);
        contarDuracionVideos(videos);

        // Marcar ejercicios como revisados
        System.out.println();
        marcarEjerciciosRevisados(materiales);

        // Filtrar por autor (Mario)
        filtrarPorAutor(materiales, material -> material.autor.equals("Mario"));
    }
}