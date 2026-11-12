package modelo;

public enum Zona {
    CENTRO(6000),
    NORTE(8000),
    SUR(8000),
    ORIENTE(15000);

    private final double tarifa;

    Zona(double valorTarifa) {tarifa = valorTarifa;}

    public double getTarifa() {return tarifa}
}
