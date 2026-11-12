package app;

import Excepciones.CapacidadExcedidaException;
import Excepciones.GuiaDuplicadaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Despacho {

    private static int consecutivo = 0;

    private final String codigo;
    private final String mensajero;
    private final List<Paquete> paquetes;

    public Despacho(String nombreMensajero) {
        consecutivo++;
        codigo = "D-" + consecutivo;
        mensajero = nombreMensajero;
        paquetes = new ArrayList<>();
        System.out.println("[Despacho creado] " + codigo + " para " + mensajero);
    }

    /**
     * Sube un paquete a la moto. Si la guía ya está o si se supera el límite
     * de 30 kg, lanza la excepción y la lista queda EXACTAMENTE como estaba.
     */
    public void agregarPaquete(Paquete nuevo) throws GuiaDuplicadaException, CapacidadExcedidaException {
        // contains() usa el equals de Paquete -> compara por guía
        if (paquetes.contains(nuevo)) {
            throw new GuiaDuplicadaException(nuevo.getGuia(), codigo);
        }
        double pesoActual = getPesoTotal();
        if (UtilEnvios.superaCapacidad(pesoActual, nuevo.getPeso())) {
            throw new CapacidadExcedidaException(nuevo.getGuia(), codigo, pesoActual, nuevo.getPeso());
        }
        paquetes.add(nuevo);
        System.out.println("  [Registrado] " + nuevo.getGuia() + " en " + codigo
                + " (carga: " + UtilEnvios.formatearPeso(getPesoTotal()) + " de "
                + UtilEnvios.formatearPeso(UtilEnvios.PESO_MAXIMO_DESPACHO) + ")");
    }

    /** Copia ordenada; la lista original no se altera. */
    public List<Paquete> getPaquetesEnOrdenDeEntrega() {
        List<Paquete> copia = new ArrayList<>(paquetes);
        copia.sort(new ComparadorEntrega());
        return copia;
    }

    public double getPesoTotal() {
        double total = 0;
        for (Paquete p : paquetes) {
            total += p.getPeso();
        }
        return total;
    }

    public double getCostoTotal() {
        double total = 0;
        for (Paquete p : paquetes) {
            total += p.calcularCosto();   // polimorfismo: cada servicio calcula el suyo
        }
        return total;
    }

    /** Advertencias de todos los paquetes que requieren manejo especial, en orden de entrega. */
    public List<String> getAdvertencias() {
        List<String> advertencias = new ArrayList<>();
        for (Paquete p : getPaquetesEnOrdenDeEntrega()) {
            if (p instanceof ManejoEspecial) {
                ManejoEspecial especial = (ManejoEspecial) p;   // downcasting
                advertencias.add(especial.getAdvertencia());
            }
        }
        return advertencias;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensajero() {
        return mensajero;
    }

    /** Vista de solo lectura: desde afuera nadie puede meter/sacar paquetes saltándose las reglas. */
    public List<Paquete> getPaquetes() {
        return Collections.unmodifiableList(paquetes);
    }

    @Override
    public String toString() {
        return "Despacho " + codigo + " – " + mensajero + " (" + paquetes.size() + " paquetes, "
                + UtilEnvios.formatearPeso(getPesoTotal()) + ")";
    }
}

