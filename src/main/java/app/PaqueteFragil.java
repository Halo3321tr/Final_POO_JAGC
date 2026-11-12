package app;

import Excepciones.EnvioException;

public class PaqueteFragil extends Paquete implements ManejoEspecial {

    private static final double RECARGO_MANEJO = 5000.0;

    private final String contenido;

    public PaqueteFragil(String numeroGuia, Zona zonaDestino, double pesoKg, String contenidoPaquete)
            throws EnvioException {
        super(numeroGuia, zonaDestino, pesoKg);
        UtilEnvios.validarContenido(numeroGuia, contenidoPaquete);
        contenido = contenidoPaquete.trim();
        System.out.println("  [Creado] Paquete Frágil " + numeroGuia + " -> " + zonaDestino
                + " (" + UtilEnvios.formatearPeso(pesoKg) + ", contenido: " + contenido + ")");
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public double calcularCosto() {
        return calcularCostoBase() + RECARGO_MANEJO;
    }

    @Override
    public String getNombreServicio() {
        return "Frágil (" + contenido + ")";
    }

    @Override
    public String getDescripcionAjuste() {
        return "+" + UtilEnvios.formatearDinero(RECARGO_MANEJO);
    }

    @Override
    public String getAdvertencia() {
        return getGuia() + " · FRÁGIL (" + contenido + ")";
    }
}
