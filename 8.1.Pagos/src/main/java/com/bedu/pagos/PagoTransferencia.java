package com.bedu.pagos;

public class PagoTransferencia implements MetodoPago {
    @Override
    public void pago(double monto) {
        System.out.println("Procesando pago con transferencia bancaria por $" + String.format("%,.2f", monto));
    }
}
