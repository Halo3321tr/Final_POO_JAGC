package app;

import java.util.List;

public class GeneradorManifiesto {

    private static final String FORMATO_FILA = "%-8s %-26s %-9s %9s %12s %8s %13s%n";

    public GeneradorManifiesto() {
        System.out.println("[Generador de manifiestos listo]");
    }

    public String generar(Despacho despacho) {
        StringBuilder sb = new StringBuilder();
        String linea = "-".repeat(91) + "\n";

        sb.append("\n=== Manifiesto: Despacho ").append(despacho.getCodigo())
                .append(" – Mensajero(a): ").append(despacho.getMensajero()).append(" ===\n");
        sb.append(linea);
        sb.append(String.format(FORMATO_FILA, "Guía", "Servicio", "Zona", "Peso",
                "Costo base", "Ajuste", "Costo envío"));
        sb.append(linea);

        for (Paquete p : despacho.getPaquetesEnOrdenDeEntrega()) {
            sb.append(String.format(FORMATO_FILA,
                    p.getGuia(),
                    p.getNombreServicio(),
                    p.getZona(),
                    UtilEnvios.formatearPeso(p.getPeso()),
                    UtilEnvios.formatearDinero(p.calcularCostoBase()),
                    p.getDescripcionAjuste(),
                    UtilEnvios.formatearDinero(p.calcularCosto())));
        }
        sb.append(linea);

        List<String> advertencias = despacho.getAdvertencias();
        String textoAdvertencias = advertencias.isEmpty() ? "Ninguna" : String.join(" | ", advertencias);

        sb.append("Peso total: ").append(UtilEnvios.formatearPeso(despacho.getPesoTotal()))
                .append(" · Costo total del despacho: $").append(UtilEnvios.formatearDinero(despacho.getCostoTotal()))
                .append("\nAdvertencias: ").append(textoAdvertencias).append("\n");
        return sb.toString();
    }
}

