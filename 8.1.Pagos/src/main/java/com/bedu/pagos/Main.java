package com.bedu.pagos;

public class Main {
    public static void main(String[] args) {
        ProcesoPago tarjeta = new ProcesoPago(new PagoTarjeta());
        tarjeta.proceso(1500.00);

        ProcesoPago paypal = new ProcesoPago(new PagoPayPal());
        paypal.proceso(8500.00);

        ProcesoPago cripto = new ProcesoPago(new CriptoPago());
        cripto.proceso(10557.00);

        ProcesoPago transferencia = new ProcesoPago(new PagoTransferencia());
        transferencia.proceso(150897.27);
    }
}

