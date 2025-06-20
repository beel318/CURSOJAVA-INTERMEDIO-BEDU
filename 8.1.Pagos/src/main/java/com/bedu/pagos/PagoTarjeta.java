package com.bedu.pagos;

public class PagoTarjeta implements MetodoPago {
    @Override
    public void pago(double monto) {
        System.out.println("Procesando pago con tarjeta por $" + String.format("%,.2f", monto));
    }
}
