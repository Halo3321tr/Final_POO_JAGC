package Excepciones;

import app.UtilEnvios;

public class PesoExcedidoException extends EnvioException {
    public PesoExcedidoException(String guia, double peso) {
        super("Paquete " + guia + ": pesa " + UtilEnvios.formatearPeso(peso)
                + " y los paquetes de más de " + UtilEnvios.formatearPeso(UtilEnvios.PESO_MAXIMO_PAQUETE)
                + " no se pueden llevar en moto.");
    }
}

