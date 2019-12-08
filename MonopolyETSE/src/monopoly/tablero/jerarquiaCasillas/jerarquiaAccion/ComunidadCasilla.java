package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacionGUI.menuGUI.registroGUI.ConsolaInterfaz;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.Tablero;

public class ComunidadCasilla extends Accion{

    public ComunidadCasilla(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    @Override
    public void ejecutarAccion(Jugador jugador) throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException, EdificiosSolarException, NumeroIncorrectoException {

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        ConsolaInterfaz.imprimir("¡Carta de comunidad! Haz click encima de las cartas de comunidad.");

        // Se pone en el tablero la opción de que se ha caido en una casilla de comunidad / suerte

        Juego.setEstarComunidad(true);

    }

}
