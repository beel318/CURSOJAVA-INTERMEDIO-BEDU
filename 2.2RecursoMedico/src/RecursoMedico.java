import java.util.concurrent.locks.ReentrantLock;

class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println(profesional + " intentando acceder a " + nombre);
        lock.lock(); // Inicia sección crítica
        try {
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); // Simula el uso del recurso
            System.out.println(profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println(profesional + " fue interrumpido.");
        } finally {
            lock.unlock(); // Libera el recurso
        }
    }
}