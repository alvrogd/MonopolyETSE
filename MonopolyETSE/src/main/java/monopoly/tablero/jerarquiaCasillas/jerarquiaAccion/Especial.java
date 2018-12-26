package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.EstarBancarrotaException;
import monopoly.jugadores.excepciones.ImposibleCambiarModoException;
import monopoly.jugadores.excepciones.ImposibleMoverseException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.Tablero;

public abstract class Especial extends Accion{

    public Especial(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    public abstract void ejecutarAccion(Jugador jugador);

}
