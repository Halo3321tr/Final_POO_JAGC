package Excepciones;

import app.UtilEnvios;

public class CapacidadExcedidaException extends EnvioException {
    public CapacidadExcedidaException(String guia, String codigoDespacho, double pesoActual, double pesoPaquete) {
        super("Paquete " + guia + ": no cabe en el despacho " + codigoDespacho + ". Carga actual "
                + UtilEnvios.formatearPeso(pesoActual) + " + " + UtilEnvios.formatearPeso(pesoPaquete) + " = "
                + UtilEnvios.formatearPeso(pesoActual + pesoPaquete) + " supera el límite de "
                + UtilEnvios.formatearPeso(UtilEnvios.PESO_MAXIMO_DESPACHO)
                + ". El paquete no se subió y el despacho quedó como estaba.");
    }
}