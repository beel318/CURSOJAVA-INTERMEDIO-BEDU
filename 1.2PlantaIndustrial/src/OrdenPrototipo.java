class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    //Constructor con herencia y atributo adicional
    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    //Metodo sobreescrito
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPrototipo - Código: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}