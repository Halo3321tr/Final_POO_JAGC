package Excepciones;

public class GuiaDuplicadaException extends EnvioException {
    public GuiaDuplicadaException(String guia, String codigoDespacho) {
        super("Paquete " + guia + ": ya existe un paquete con esa guía en el despacho "
                + codigoDespacho + ". No se registró.");
    }
}
