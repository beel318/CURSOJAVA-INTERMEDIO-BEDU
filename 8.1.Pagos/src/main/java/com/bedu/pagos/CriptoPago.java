package com.bedu.pagos;

public class CriptoPago implements MetodoPago {
    @Override
    public void pago(double monto) {
        System.out.println("Procesando pago con criptomonedas por $" + String.format("%,.2f", monto));
    }
}
