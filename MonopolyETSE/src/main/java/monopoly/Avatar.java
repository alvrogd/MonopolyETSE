package monopoly;

import java.util.Collection;
import java.util.Random;

public class Avatar {

    /* Atributos */

    // Jugador al que pertenece
    private final Jugador jugador;

    // Tablero en el que se encuentra
    private final Tablero tablero;

    // Se resetea a false al completar una vuelta
    private boolean haEstadoCarcel;
    // Si se encuentra en la cárcel actualmente
    private boolean encarcelado;

    // Vueltas completadas en el tablero
    private int vueltas;
    // Casilla actual
    private Casilla posicion;

    // Representación ASCII en el dibujado del tablero
    private final char identificador;
    // Uno de los cuatro tipos de avatares disponibles
    private final TipoAvatar tipo;

    // Moverse de casilla en casilla o empleando el movimiento especial del tipo de avatar
    private boolean movimientoEstandar;



    /* Constructores */

    public Avatar(Jugador jugador, Tablero tablero, TipoAvatar tipo, Casilla casillaInicial) {

        if (jugador == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Error: tablero no inicializado.");
            System.exit(1);
        }

        if (tipo == null) {
            System.err.println("Error: tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.out.println("Error: casilla inicial no inicializada.");
            System.exit(1);
        }

        this.jugador = jugador;

        this.tablero = tablero;

        haEstadoCarcel = false;
        encarcelado = false;

        vueltas = 0;
        posicion = casillaInicial;

        // Identificador aleatorio y único
        Random random = new Random();
        boolean identificadorUnico;
        char identificadorAleatorio;
        Collection<Avatar> avatares = tablero.getAvataresContenidos().values();

        do {
            identificadorAleatorio = (char) random.nextInt(256);    // Carácter ASCII del 0 al 255
            identificadorUnico = true;
            for (Avatar avatar : avatares) {
                if (avatar.getIdentificador() == identificadorAleatorio)
                    identificadorUnico = false;
            }
        } while (!identificadorUnico);

        identificador = identificadorAleatorio;

        this.tipo = tipo;

        movimientoEstandar = true;

    }



    /* Getters y setters */

    public Jugador getJugador() {
        return jugador;
    }


    public Tablero getTablero() {
        return tablero;
    }


    public boolean isHaEstadoCarcel() {
        return (haEstadoCarcel);
    }


    public void setHaEstadoCarcel(boolean haEstadoCarcel) {
        this.haEstadoCarcel = haEstadoCarcel;
    }


    public boolean isEncarcelado() {
        return (encarcelado);
    }


    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }


    public int getVueltas() {
        return (vueltas);
    }


    public void setVueltas(int vueltas) {

        if (vueltas < 0) {
            System.err.println("Error: el número de vueltas de un avatar no puede ser menor a 0.");
            return;
        }

        this.vueltas = vueltas;
    }


    public Casilla getPosicion() {
        return (posicion);
    }


    public void setPosicion(Casilla posicion) {

        if (posicion == null) {
            System.err.println("Error: casilla no inicializada.");
            return;
        }

        this.posicion = posicion;

    }


    public char getIdentificador() {
        return (identificador);
    }


    public TipoAvatar getTipo() {
        return (tipo);
    }


    public boolean isMovimientoEstandar() {
        return (movimientoEstandar);
    }


    public void setMovimientoEstandar(boolean movimientoEstandar) {
        this.movimientoEstandar = movimientoEstandar;
    }



    /* Métodos */

    public void salirCarcel() {

        if (!isEncarcelado()) {
            System.err.println("Error: el avatar no se encuentra en la cárcel.");
            return;
        }

        setEncarcelado(false);

    }


    // todo añadir comportamiento en función de la casilla a la que se ha llegado
    // todo identificar casilla empleado su grupo
    public void mover(int numeroCasillas, boolean dobles) {

        if (numeroCasillas < 2) {
            System.err.println("Error: el número sacado en una tirada no puede ser menor que 2.");
            return;
        }

        int posicionFinal = getPosicion().getPosicionEnTablero() + numeroCasillas;
        posicion = getTablero().getCasillas().get(posicionFinal / 10).get(posicionFinal % 10);


    }


    @Override
    public boolean equals(Object o) {

        // Si este objeto y el objeto pasado como parámetro apuntan a la misma dirección de memoria
        if (this == o) return (true);

            // Si el parámetro referencia a null
        else if (o == null) return (false);

        // Si el parámetro no es un objeto de tipo Avatar
        if (getClass() != o.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder llamar a sus métodos
        final Avatar otro = (Avatar) o;

        // Si el identificador es distinto; son el mismo objeto
        return (getIdentificador() == otro.getIdentificador());

    }

}
