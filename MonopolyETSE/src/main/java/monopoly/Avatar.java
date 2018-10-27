package monopoly;

public class Avatar {

    /* Atributos */
    private boolean haEstadoCarcel;    // Se resetea a false al completar una vuelta
    private boolean estaCarcel;

    private int vueltas;
    private Casilla posicion;

    private final char identificador;
    private final TipoAvatar tipo;

    private boolean movimientoEstandar;


    /* Constructores */
    public Avatar(TipoAvatar tipo, Casilla casillaInicial) {

        if (tipo == null) {
            System.out.println("El tipo de avatar referencia a null");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.out.println("La casilla referencia a null");
            System.exit(1);
        }

        haEstadoCarcel = false;
        estaCarcel = false;

        vueltas = 0;
        posicion = casillaInicial;

        identificador = 'h';

        // Identificador
        this.tipo = tipo;

        movimientoEstandar = true;

    }

    public char getIdentificador() {
        return identificador;
    }
}
