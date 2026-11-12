package Excepciones;

public class ContenidoInvalidoException extends EnvioException {
    public ContenidoInvalidoException(String guia) {
        super("Paquete " + guia + ": un envío frágil debe indicar qué contiene; no se acepta sin contenido.");
    }
}