abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    //Constructor
    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    //Metodo base - abstracto
    public abstract void mostrarResumen();
}
