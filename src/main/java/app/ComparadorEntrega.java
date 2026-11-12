package app;

import java.util.Comparator;

public class ComparadorEntrega implements Comparator<Paquete> {

    @Override
    public int compare(Paquete a, Paquete b) {
        // 1) Express primero: true va antes que false
        int porPrioridad = Boolean.compare(b.esPrioritario(), a.esPrioritario());
        if (porPrioridad != 0) {
            return porPrioridad;
        }
        // 2) Orden de la ruta
        int porRuta = Integer.compare(a.getZona().getOrdenRuta(), b.getZona().getOrdenRuta());
        if (porRuta != 0) {
            return porRuta;
        }
        // 3) Guía de menor a mayor (todas tienen formato EY#### -> el orden alfabético sirve)
        return a.getGuia().compareTo(b.getGuia());
    }
}