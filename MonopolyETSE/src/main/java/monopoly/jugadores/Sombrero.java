package monopoly.jugadores;

import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.Tablero;

public class Sombrero extends Avatar {

    /* Atributos */

    // Se establece al cambiar a modo avanzado si el movimiento se efectuará hacia la izquierda o hacia la derecha
    private boolean moverseANorte;



    /* Constructor */

    /**
     * Constructor que crea un avatar de la clase Sombrero para un jugador
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Sombrero(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        super(jugador, tablero, casillaInicial);
    }



    /* Getters y setters */

    public boolean isMoverseANorte() {
        return moverseANorte;
    }

    public void setMoverseANorte(boolean moverseANorte) {
        this.moverseANorte = moverseANorte;
    }



    /* Métodos */

    /**
     * Se comprueba, en función de la tirada obtenida, si no es posible realizar otra nueva tirada más tarde
     * @param primeraTirada valor del primer dado
     * @param segundaTirada valor del segundo dado
     * @return              si no es posible realizar otra tirada más
     */
    @Override
    public boolean noMasTiradas(int primeraTirada, int segundaTirada) {

        if (isMovimientoEstandar())
            return (super.noMasTiradas(primeraTirada, segundaTirada));

        else
            // Si no ha sacado un 4 o más o ha hecho el máximo de tiradas en un turno
            return (getJugador().getTiradasEnTurno() >= 3 || (primeraTirada + segundaTirada) < 4);
    }


    /**
     * Se cambia el modo de movimiento del avatar, alternando entre los dos disponibles
     *
     * @param forzar si se debe forzar el cambio de movimiento a pesar de las reglas del juego
     */
    @Override
    public void switchMovimiento(boolean forzar, boolean splash) {

        super.switchMovimiento(forzar, splash);

        // Se actualiza la dirección a la que moverse en caso de establecer el movimiento avanzado
        if (!isMovimientoEstandar()) {

            int posicion = getPosicion().getPosicionEnTablero();
            setMoverseANorte((posicion % 40 == 0) || (posicion % 40 == 1));
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
            return (actualizarPosicionSombrero(numeroCasillas));
    }


    /**
     * Se actualiza la posición del avatar empleando el movimiento avanzado de una esfinge
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    public int actualizarPosicionSombrero(int numeroCasillas) {

        // Se asigna inicialmente el número de casilla inicial
        int posicionFinal = getPosicion().getPosicionEnTablero();


        if (numeroCasillas >= 4) {

            // Se comprueba si la siguiente columna de destino es la oeste o la este (el avatar debe ir a la oeste si
            // se encuentra en la fila inferior o en la columna derehca
            int siguienteColumna = ((posicionFinal % 40 == 3) || (posicionFinal % 40 == 0)) ? 1 : 3;

            // Si el avatar se encuentra en una de las filas, se mueve a la segunda casilla de la correspondiente
            // columna
            if ((posicionFinal % 40 == 0) || (posicionFinal % 40 == 2))
                posicionFinal = (siguienteColumna * 10) + 1;

                // En caso contrario, se va a la columna de destino avanzando una posición
            else {

                // Si se va a la columna este moviéndose al norte, se retroceden posiciones
                if (siguienteColumna == 3 && isMoverseANorte() )
                    posicionFinal = 40 - 1 - posicionFinal % 10;

                    // En el caso de la ir a la columna oeste hacia el norte, se avanzan posiciones
                else if( siguienteColumna != 3 && isMoverseANorte())
                    posicionFinal = 20 + 1 - posicionFinal % 10;

                    // De tener que ir a la columna este moviéndose al sur, se avanzan posiciones
                else if( siguienteColumna == 3 && !isMoverseANorte() )
                    posicionFinal = 40 + 1 - posicionFinal % 10;

                    // La última opción es tener que ir a la columna oeste moviéndose hacia el sur, teniendo que
                    // retroceder
                else
                    posicionFinal = 20 - 1 - posicionFinal % 10;


                // Si se ha llegado al final de un lado, se vuelve al inicio de este
                if( posicionFinal % 10 == 0 )

                    if( siguienteColumna == 3 && isMoverseANorte() )
                        posicionFinal += 10;

                    else if( siguienteColumna != 3 && isMoverseANorte())
                        posicionFinal -= 10;

                    else if( siguienteColumna == 3 && !isMoverseANorte() )
                        posicionFinal -=10;

                    else
                        posicionFinal += 10;

            }

            // Si ha pasado por la casilla de salida
            if (posicionFinal >= 40)
                setHaPasadoSalida(true);

        }

        // Si se ha sacado menos de un 4, se deshacen las compras de propiedades y edificios, y se eliminan los pagos
        // obtenidos por premios en el último turno
        else
            getJugador().revertirAcciones();


        setCasillasRestantesPorMoverse(getCasillasRestantesPorMoverse() - 1);
        return (posicionFinal % 40);
    }
}
