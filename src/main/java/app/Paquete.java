package app;

import Excepciones.EnvioException;

import java.util.Objects;

public abstract class Paquete {

    private final String guia;
    private final double peso;
    private final Zona zona;

    protected Paquete(String numeroGuia, Zona zonaDestino, double pesoKg) throws EnvioException {
        // Se valida ANTES de asignar: si algo falla, el objeto nunca existe.
        UtilEnvios.validarGuia(numeroGuia);
        UtilEnvios.validarZona(numeroGuia, zonaDestino);
        UtilEnvios.validarPeso(numeroGuia, pesoKg);
        guia = numeroGuia;
        zona = zonaDestino;
        peso = pesoKg;
    }


    public double calcularCostoBase() {
        return zona.getTarifa() + UtilEnvios.COSTO_POR_KG * peso;
    }

    public abstract double calcularCosto();


    public abstract String getNombreServicio();

    public abstract String getDescripcionAjuste();

    public boolean esPrioritario() {
        return false;
    }

    public String getGuia() {
        return guia;
    }

    public double getPeso() {
        return peso;
    }

    public Zona getZona() {
        return zona;
    }


    @Override
    public boolean equals(Object otro) {
        if (otro == this) {
            return true;
        }
        if (!(otro instanceof Paquete)) {
            return false;
        }
        Paquete otroPaquete = (Paquete) otro;
        return guia.equals(otroPaquete.getGuia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(guia);
    }

    @Override
    public String toString() {
        return guia + " | " + getNombreServicio() + " | " + zona + " | "
                + UtilEnvios.formatearPeso(peso) + " | $" + UtilEnvios.formatearDinero(calcularCosto());
    }
}

