package monopoly.jugadores;

import monopoly.jugadores.excepciones.EstarBancarrotaException;
import monopoly.jugadores.excepciones.ImposibleMoverseException;
import monopoly.jugadores.excepciones.NoEstarEncarceladoException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;

public class Pelota extends Avatar {

    /* Atributos */

    // Si se ha movido hacia atrás por primera vez
    private boolean haMovidoAtras;

    // Si se ha movido sus 4 casillas iniciales
    private boolean haMovido4Casillas;


    /**
     * Constructor que crea un avatar de la clase Pelota para un jugador
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Pelota(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        super(jugador, tablero, casillaInicial);

        this.haMovidoAtras = false;
        this.haMovido4Casillas = false;
    }


    /* Getters y setters */

    public boolean isHaMovidoAtras() {
        return haMovidoAtras;
    }

    public void setHaMovidoAtras(boolean haMovidoAtras) {
        this.haMovidoAtras = haMovidoAtras;
    }

    public boolean isHaMovido4Casillas() {
        return haMovido4Casillas;
    }

    public void setHaMovido4Casillas(boolean haMovido4Casillas) {
        this.haMovido4Casillas = haMovido4Casillas;
    }



    /* Métodos */

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
        else {

            // Se avanza si ya se ha movido cuatro casillas (indicativo de haber avanzado en una iteración
            // anterior) o si el número de casillas a moverse es mayor o igual a 4 (por el caso inicial)
            if (isHaMovido4Casillas() || numeroCasillas >= 4)
                return (actualizarPosicionPelota(numeroCasillas, true));
            else
                return (actualizarPosicionPelota(numeroCasillas, false));

        }
    }


    /**
     * Se actualiza la posición del avatar empleando el movimiento avanzado de una pelota
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    private int actualizarPosicionPelota(int numeroCasillas, boolean avanzar) {

        // Se asigna inicialmente el número de casilla inicial
        int posicionFinal = getPosicion().getPosicionEnTablero();


        if (avanzar) {

            // Si aún no se ha movido las 4 casillas iniciales
            if (!isHaMovido4Casillas()) {
                numeroCasillas -= 4;
                posicionFinal += 4;
                setHaMovido4Casillas(true);

                // Si aún se puede mover más el avatar, se avanza hasta la 5ª casilla dado que será donde deba parar
                if (numeroCasillas > 0) {
                    numeroCasillas--;
                    posicionFinal++;
                }
            }

            // Si ya se han avanzado las 4 casillas iniciales y se ha vuelto a llamar a esta función, la tirada del
            // jugador fue de al menos 6 casillas
            else {

                // Por lo tanto, o bien se va a la siguiente casilla de tirada impar
                if (numeroCasillas > 1) {
                    numeroCasillas -= 2;
                    posicionFinal += 2;
                }

                // O bien se avanza a la siguiente casilla dado que el avatar sólo puede moverse una más
                else {
                    numeroCasillas--;
                    posicionFinal++;
                }

            }

            // Si ha pasado por la casilla de salida
            if (posicionFinal >= 40)
                setHaPasadoSalida(true);

        } else {

            // Si aún no se ha movido hacia atrás, se retrocede una casilla dado que debe parar en ella
            if (!isHaMovidoAtras()) {
                numeroCasillas--;
                posicionFinal--;
                setHaMovidoAtras(true);

            }

            // Si ya se ha avanzado hacia atrás y se ha vuelto a llamar a esta función, la tirada del jugador fue de
            // de una suma total de 2 o 3
            else {

                // Por lo tanto, o bien se va a la siguiente casilla de tirada impar
                if (numeroCasillas > 1) {
                    numeroCasillas -= 2;
                    posicionFinal -= 2;
                }

                // O bien se avanza a la casilla anterior dado que el avatar sólo puede moverse una más
                else {
                    numeroCasillas--;
                    posicionFinal--;
                }

            }

            // Si ha pasado por la casilla de salida (debe considerarse que ahora se mueve hacia atrás)
            if (posicionFinal < 0) {
                setHaPasadoSalida(true);

                // Y se corrige además el número de casilla al que el avatar debe ir
                posicionFinal = 40 + posicionFinal;
            }


        }

        setCasillasRestantesPorMoverse(numeroCasillas);
        return (posicionFinal % 40);
    }


    /**
     * Mueve al jugador de un avatar, en caso de no estar encarcelado, o estarlo y sacar dobles; si se ha estado en la
     * cárcel el número máximo de turnos permitidos, se fuerza su salida
     *
     * @param numeroCasillas cantidad de casillas a moverse
     * @param dobles         si los dados han dado el mismo valor
     */
    @Override
    public void mover(int numeroCasillas, boolean dobles) throws ImposibleMoverseException, NoSerPropietarioException,
            EstarBancarrotaException, NoEstarEncarceladoException  {

        // Se indica que aún no se ha movido las 4 casillas iniciales
        setHaMovido4Casillas(false);
        // Se indica que aún no se ha movido hacia atrás
        setHaMovidoAtras(false);

        super.mover(numeroCasillas, dobles);
    }

}