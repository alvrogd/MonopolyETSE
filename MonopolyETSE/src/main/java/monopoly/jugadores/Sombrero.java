package monopoly.jugadores;

import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;

public class Sombrero extends Avatar {

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
}
