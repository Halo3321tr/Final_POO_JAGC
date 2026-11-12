package app;

import Excepciones.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

public final class UtilEnvios {

    public static final double PESO_MAXIMO_PAQUETE = 20.0;   // kg que se pueden llevar en moto
    public static final double PESO_MAXIMO_DESPACHO = 30.0;  // kg que carga una moto en total
    public static final double COSTO_POR_KG = 1000.0;        // $1.000 por kilogramo

    private static final Pattern FORMATO_GUIA = Pattern.compile("EY\\d{4}");
    private static final double TOLERANCIA = 1e-9;

    private static final DecimalFormatSymbols SIMBOLOS = new DecimalFormatSymbols();
    static {
        SIMBOLOS.setGroupingSeparator('.');
        SIMBOLOS.setDecimalSeparator(',');
    }
    private static final DecimalFormat FORMATO_DINERO = new DecimalFormat("#,##0", SIMBOLOS);
    private static final DecimalFormat FORMATO_PESO = new DecimalFormat("0.0", SIMBOLOS);

    private UtilEnvios() {

    }



    public static void validarGuia(String guia) throws GuiaInvalidaException {
        if (guia == null || !FORMATO_GUIA.matcher(guia).matches()) {
            throw new GuiaInvalidaException(guia);
        }
    }

    public static void validarZona(String guia, Zona zona) throws ZonaInvalidaException {
        if (zona == null) {
            throw new ZonaInvalidaException(guia);
        }
    }

    public static void validarPeso(String guia, double peso)
            throws PesoInvalidoException, PesoExcedidoException {
        if (peso <= 0) {
            throw new PesoInvalidoException(guia, peso);
        }
        if (peso > PESO_MAXIMO_PAQUETE + TOLERANCIA) {
            throw new PesoExcedidoException(guia, peso);
        }
    }

    public static void validarContenido(String guia, String contenido) throws ContenidoInvalidoException {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new ContenidoInvalidoException(guia);
        }
    }


    public static boolean superaCapacidad(double pesoActual, double pesoNuevo) {
        return pesoActual + pesoNuevo > PESO_MAXIMO_DESPACHO + TOLERANCIA;
    }


    public static String formatearDinero(double valor) {
        return FORMATO_DINERO.format(valor);
    }


    public static String formatearPeso(double kg) {
        return FORMATO_PESO.format(kg) + " kg";
    }
}
