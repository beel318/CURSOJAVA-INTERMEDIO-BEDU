package com.bedu.pagos;

public class ProcesoPago {
    private final MetodoPago metodoPago;

    public ProcesoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void proceso(double monto) {
        metodoPago.pago(monto);
    }
}
