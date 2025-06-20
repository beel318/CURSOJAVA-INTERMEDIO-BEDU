class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    //Constructor con herencia y atributo adicional
    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    //Metodo sobreescrito
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPersonalizada - Código: " + codigo + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }

    //Metodo para costo adicional
    public void aplicarCostoAdicional(int costo) {
        System.out.println("Orden " + codigo + " ajustada con costo adicional de $" + costo);
    }
}