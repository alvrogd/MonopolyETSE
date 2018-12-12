package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;

public class Esfinge extends Avatar {

    /* Atributos */

    // Se establece al cambiar a modo avanzado si el movimiento se efectuará hacia la izquierda o hacia la derecha
    private boolean moverseAOeste;

    /**
     * Constructor que crea un avatar de la clase Esfinge para un jugador
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Esfinge(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        super(jugador, tablero, casillaInicial);

        this.moverseAOeste = false;
    }



    /* Getters y setters */

    public boolean isMoverseAOeste() {
        return moverseAOeste;
    }

    public void setMoverseAOeste(boolean moverseAOeste) {
        this.moverseAOeste = moverseAOeste;
    }



    /* Métodos */

    /**
     * Se cambia el modo de movimiento del avatar, alternando entre los dos disponibles
     *
     * @param forzar si se debe forzar el cambio de movimiento a pesar de las reglas del juego
     */
    @Override
    public void switchMovimiento(boolean forzar, boolean splash) {

        super.switchMovimiento( forzar, splash );

        // Se actualiza la dirección a la que moverse en caso de establecer el movimiento avanzado
        if( !isMovimientoEstandar() ) {

            int posicion = getPosicion().getPosicionEnTablero();
            setMoverseAOeste( ( posicion % 40 == 3 ) || ( posicion % 40 == 0 ) );
        }
    }


    /**
     * Se calcula la posición de la casilla a la que un avatar debe moverse, dado un número de casillas a avanzar; el
     * funcionamiento depende del modo de movimiento establecido actualmente
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla destino
     */
    public int calcularNuevaPosicion(int numeroCasillas) {

        // Si el jugador se encuentra en movimiento estándar, se avanza con normalidad
        if (isMovimientoEstandar())
            return (actualizarPosicionNormal(numeroCasillas));

        // En caso contrario
        else
            return (actualizarPosicionEsfinge(numeroCasillas));
    }


    /**
     * Se actualiza la posición del avatar empleando el movimiento avanzado de una esfinge
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    public int actualizarPosicionEsfinge(int numeroCasillas) {

        // Se asigna inicialmente el número de casilla inicial
        int posicionFinal = getPosicion().getPosicionEnTablero();


        if (numeroCasillas >= 4) { // todo diferenciar entre empezar a moverse desde un lado o desde arriba y abajo

            // Se comprueba si la siguiente fila de destino es la norte o la sur (el avatar debe ir a la norte si se
            // encuentra en la fila superior o en la columna izquierda
            int siguienteFila = ( ( posicionFinal % 40 == 0 ) || ( posicionFinal % 40 == 1 ) ) ? 2 : 0;



            posicionFinal += numeroCasillas;

            // Si ha pasado por la casilla de salida
            if (posicionFinal >= 40)
                setHaPasadoSalida(true);

        } else {

            // todo deshacer cambios de la última tirada

        }

        setCasillasRestantesPorMoverse(0);
        return (posicionFinal % 40);
    }

}
