package app;

public enum Zona {
    CENTRO("Centro", 6000, 1),
    NORTE("Norte", 8000, 2),
    SUR("Sur", 8000, 3),
    ORIENTE("Oriente", 15000, 4);

    private final String nombre;
    private final double tarifa;
    private final int ordenRuta;

    Zona(String nombreZona, double tarifaFija, int posicionEnRuta) {
        nombre = nombreZona;
        tarifa = tarifaFija;
        ordenRuta = posicionEnRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTarifa() {
        return tarifa;
    }

    public int getOrdenRuta() {
        return ordenRuta;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

