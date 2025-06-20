class OrdenMasa extends OrdenProduccion {
    //Constructor con herencia
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    //Metodo sobreescrito
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenMasa - Código: " + codigo + " - Cantidad: " + cantidad);
    }
}