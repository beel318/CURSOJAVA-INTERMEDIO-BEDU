import java.util.concurrent.Callable;

class SistemaSoporteVital implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(700);
        return "Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}