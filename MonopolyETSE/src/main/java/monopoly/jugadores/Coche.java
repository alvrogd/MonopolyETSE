package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;

public class Coche extends Avatar {

    /* Atributos */


    /**
     * Constructor que crea un avatar de la clase Coche para un jugador
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Coche(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        super(jugador, tablero, casillaInicial);
    }



    /* Métodos */

    /**
     * Se comprueba si se ha sacado el número máximo de dobles permitidos para el avatar del jugador
     * @return si se ha sacado el número máximo de dobles permitidos
     */
    @Override
    public boolean doblesMaximos(){

        return( getJugador().getTiradasEnTurno() == 3 && !isMovimientoEstandar());
    }


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
            return (getJugador().getTiradasEnTurno() >= 4 || (primeraTirada + segundaTirada) < 4);
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
            return (actualizarPosicionCoche(numeroCasillas));
    }


    /**
     * Se actualiza la posición del avatar empleando el movimiento avanzado de un coche
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    public int actualizarPosicionCoche(int numeroCasillas) {

        // Se asigna inicialmente el número de casilla inicial
        int posicionFinal = getPosicion().getPosicionEnTablero();


        if (numeroCasillas >= 4) {

            posicionFinal += numeroCasillas;

            // Si ha pasado por la casilla de salida
            if (posicionFinal >= 40)
                setHaPasadoSalida(true);

        } else {

            posicionFinal -= numeroCasillas;

            // Si ha pasado por la casilla de salida (debe considerarse que ahora se mueve hacia atrás)
            if (posicionFinal < 0) {
                setHaPasadoSalida(true);

                // Y se corrige además el número de casilla al que el avatar debe ir
                posicionFinal = 40 + posicionFinal;
            }

            // Se indica que el jugador no puede volver a lanzar los dados ni en este turno ni en los dos siguientes
            getTablero().getJuego().setHaLanzadoDados(true);
            // Se establecen 3 turnos dado que, al finalizar este, el juego le restará un turno de penalización al
            // jugador
            getJugador().setTurnosPenalizado(3);

        }

        setCasillasRestantesPorMoverse(0);
        return (posicionFinal % 40);
    }


    /**
     * Se comprueba si el avatar puede cambiar su modo de movimiento
     *
     * @return si el avatar puede cambiar el modo de movimiento
     */
    @Override
    public boolean poderCambiarMovimiento(boolean forzar) {

        // Si es un coche y se ha movido ya
        // todo avisar de Output.sugerencia("Un coche tan sólo puede cambiar su modo de moviemiento al inicio del turno");
        return( super.poderCambiarMovimiento(forzar) && ( !getTablero().getJuego().isHaHechoUnaTirada() || forzar) );
    }

}
