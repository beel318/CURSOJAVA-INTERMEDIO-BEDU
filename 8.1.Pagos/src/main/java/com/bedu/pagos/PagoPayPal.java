package com.bedu.pagos;

public class PagoPayPal implements MetodoPago {
    @Override
    public void pago(double monto) {
        System.out.println("Procesando pago con PayPal por $" + String.format("%,.2f", monto));
    }
}
