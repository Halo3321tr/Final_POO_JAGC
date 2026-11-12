package app;

import Excepciones.EnvioException;

public class PaqueteEstandar extends Paquete {

    public PaqueteEstandar(String numeroGuia, Zona zonaDestino, double pesoKg) throws EnvioException {
        super(numeroGuia, zonaDestino, pesoKg);
        System.out.println("  [Creado] Paquete Estándar " + numeroGuia + " -> " + zonaDestino
                + " (" + UtilEnvios.formatearPeso(pesoKg) + ")");
    }

    @Override
    public double calcularCosto() {
        return calcularCostoBase();
    }

    @Override
    public String getNombreServicio() {
        return "Estándar";
    }

    @Override
    public String getDescripcionAjuste() {
        return "—";
    }
}

