package Excepciones;

import app.UtilEnvios;

public class PesoInvalidoException extends EnvioException {
    public PesoInvalidoException(String guia, double peso) {
        super("Paquete " + guia + ": peso inválido (" + UtilEnvios.formatearPeso(peso) + "). El peso debe ser mayor que 0 kg.");
    }
}

