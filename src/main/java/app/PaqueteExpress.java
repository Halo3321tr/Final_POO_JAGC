package app;

import Excepciones.EnvioException;

public class PaqueteExpress extends Paquete {

    private static final double RECARGO = 0.50;

    public PaqueteExpress(String numeroGuia, Zona zonaDestino, double pesoKg) throws EnvioException {
        super(numeroGuia, zonaDestino, pesoKg);
        System.out.println("  [Creado] Paquete Express " + numeroGuia + " -> " + zonaDestino
                + " (" + UtilEnvios.formatearPeso(pesoKg) + ")");
    }

    @Override
    public double calcularCosto() {
        return calcularCostoBase() * (1 + RECARGO);
    }

    @Override
    public String getNombreServicio() {
        return "Express";
    }

    @Override
    public String getDescripcionAjuste() {
        return "+" + Math.round(RECARGO * 100) + "%";
    }

    @Override
    public boolean esPrioritario() {
        return true;
    }
}
