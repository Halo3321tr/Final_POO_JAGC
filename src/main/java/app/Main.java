import Excepciones.EnvioException;
import app.*;

@FunctionalInterface
    private interface CreadorPaquete {
        Paquete crear() throws EnvioException;
    }

    public static void main(String[] args) {
        GeneradorManifiesto generador = new GeneradorManifiesto();

        caso1(generador);
        caso2();
        caso3(generador);
        caso4(generador);
    }

    private static void caso1(GeneradorManifiesto generador) {
        titulo("CASO 1 – Despacho de Andrés");
        Despacho despacho = new Despacho("Andrés");
        registrar(despacho, () -> new PaqueteEstandar("EY2001", Zona.ORIENTE, 5));
        registrar(despacho, () -> new PaqueteExpress("EY2002", Zona.NORTE, 1.5));
        registrar(despacho, () -> new PaqueteFragil("EY2003", Zona.CENTRO, 2, "vajilla"));
        registrar(despacho, () -> new PaqueteEstandar("EY2004", Zona.CENTRO, 8));
        registrar(despacho, () -> new PaqueteExpress("EY2005", Zona.ORIENTE, 6));
        registrar(despacho, () -> new PaqueteFragil("EY2006", Zona.SUR, 3.5, "televisor"));
        System.out.println(generador.generar(despacho));
    }

    private static void caso2() {
        titulo("CASO 2 – Paquetes con datos inválidos");
        crearSinDespacho(() -> new PaqueteEstandar("EY3001", Zona.CENTRO, -2));
        crearSinDespacho(() -> new PaqueteFragil("EY3002", Zona.NORTE, 2, ""));
        crearSinDespacho(() -> new PaqueteEstandar("EY12A4", Zona.SUR, 3));
        crearSinDespacho(() -> new PaqueteExpress("EY3003", Zona.NORTE, 22));
        System.out.println("  El programa continúa normalmente.\n");
    }

    private static void caso3(GeneradorManifiesto generador) {
        titulo("CASO 3 – Despacho de Camila");
        Despacho despacho = new Despacho("Camila");
        registrar(despacho, () -> new PaqueteEstandar("EY4001", Zona.CENTRO, 12));
        registrar(despacho, () -> new PaqueteExpress("EY4002", Zona.SUR, 10));
        registrar(despacho, () -> new PaqueteFragil("EY4003", Zona.NORTE, 9, "espejo"));
        registrar(despacho, () -> new PaqueteEstandar("EY4004", Zona.ORIENTE, 8));
        System.out.println(generador.generar(despacho));
    }

    private static void caso4(GeneradorManifiesto generador) {
        titulo("CASO 4 – Despacho de Mateo");
        Despacho despacho = new Despacho("Mateo");
        registrar(despacho, () -> new PaqueteFragil("EY5001", Zona.SUR, 3, "lámpara"));
        registrar(despacho, () -> new PaqueteEstandar("EY5001", Zona.CENTRO, 1));
        registrar(despacho, () -> new PaqueteExpress("EY5002", Zona.CENTRO, 2));
        System.out.println(generador.generar(despacho));
    }


    private static void registrar(Despacho despacho, CreadorPaquete creador) {
        try {
            Paquete paquete = creador.crear();
            despacho.agregarPaquete(paquete);
        } catch (EnvioException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }


    private static void crearSinDespacho(CreadorPaquete creador) {
        try {
            Paquete paquete = creador.crear();
            System.out.println("  [OK] " + paquete);
        } catch (EnvioException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    private static void titulo(String texto) {
        System.out.println("\n==================== " + texto + " ====================");
    }

