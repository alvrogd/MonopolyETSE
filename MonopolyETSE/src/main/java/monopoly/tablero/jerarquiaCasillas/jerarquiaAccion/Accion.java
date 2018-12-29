package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;

public abstract class Accion extends Casilla {

    public Accion(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    public abstract void ejecutarAccion(Jugador jugador) throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException, ImposibleMoverseException, EdificiosSolarException;

}
