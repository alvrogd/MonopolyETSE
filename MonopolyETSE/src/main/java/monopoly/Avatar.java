package monopoly;

import java.util.Random;

public class Avatar {

    /* Atributos */

    // Se resetea a false al completar una vuelta
    private boolean haEstadoCarcel;
    // Si se encuentra en la cárcel actualmente
    private boolean estaCarcel;

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

    public Avatar(TipoAvatar tipo, Casilla casillaInicial) {

        if (tipo == null) {
            System.err.println("Error: tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.out.println("Error: casilla inicial no inicializada.");
            System.exit(1);
        }

        haEstadoCarcel = false;
        estaCarcel = false;

        vueltas = 0;
        posicion = casillaInicial;

        // Generador de aleatorios
        Random random = new Random();
        identificador = (char) random.nextInt(256);    // Carácter ASCII del 0 al 255

        // Identificador
        this.tipo = tipo;

        movimientoEstandar = true;

    }



    /* Getters y setters */

    public boolean isHaEstadoCarcel() {
        return haEstadoCarcel;
    }


    public void setHaEstadoCarcel(boolean haEstadoCarcel) {
        this.haEstadoCarcel = haEstadoCarcel;
    }


    public boolean isEstaCarcel() {
        return estaCarcel;
    }


    public void setEstaCarcel(boolean estaCarcel) {
        this.estaCarcel = estaCarcel;
    }


    public int getVueltas() {
        return vueltas;
    }


    public void setVueltas(int vueltas) {

        if (vueltas < 0) {
            System.err.println("Error: el número de vueltas de un avatar no puede ser menor a 0.");
            return;
        }

        this.vueltas = vueltas;
    }


    public Casilla getPosicion() {
        return posicion;
    }


    public void setPosicion(Casilla posicion) {

        if (posicion == null) {
            System.err.println("Error: casilla no inicializada.");
            return;
        }

        this.posicion = posicion;

    }


    public char getIdentificador() {
        return identificador;
    }


    public TipoAvatar getTipo() {
        return tipo;
    }


    public boolean isMovimientoEstandar() {
        return movimientoEstandar;
    }


    public void setMovimientoEstandar(boolean movimientoEstandar) {
        this.movimientoEstandar = movimientoEstandar;
    }



    /* Métodos */

    public void salirCarcel() {

        if( isEstaCarcel() == false ) {
            System.err.println( "Error: el avatar no se encuentra en la cárcel.");
            return;
        }

        setEstaCarcel(false);

    }


    public void mover( int numeroCasillas ) {

        if( numeroCasillas < 2 ) {
            System.err.println( "Error: el número sacado en una tirada no puede ser menor que 2.");
            return;
        }

        for( int i = 0; i < numeroCasillas; i++ )
            setPosicion(getPosicion().getSiguienteCasilla());

    }

}
