package Excepciones;

public class ZonaInvalidaException extends EnvioException {
    public ZonaInvalidaException(String guia) {
        super("Paquete " + guia + ": debe indicar una zona de destino (Centro, Norte, Sur u Oriente).");
    }
}
