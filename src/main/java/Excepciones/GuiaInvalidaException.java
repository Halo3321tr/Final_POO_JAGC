package Excepciones;

public class GuiaInvalidaException extends EnvioException {
    public GuiaInvalidaException(String guia) {
        super("Guía inválida '" + guia + "': debe ser EY seguido de exactamente 4 dígitos (ej. EY0451).");
    }
}