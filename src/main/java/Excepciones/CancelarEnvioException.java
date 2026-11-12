package Excepciones;

public class CancelarEnvioException extends RuntimeException {
    public CancelarEnvioException(String message) {
        super("Una Vez hecho un envio, este no puede cancelarse");
    }
}
