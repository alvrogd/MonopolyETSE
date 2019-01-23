package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.Aplicacion;
import monopoly.Constantes;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.Tablero;
import monopoly.tablero.cartas.Carta;

public class SuerteCasilla extends Accion{

    public SuerteCasilla(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    @Override
    public void ejecutarAccion(Jugador jugador) throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException, EdificiosSolarException, NumeroIncorrectoException {

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        Juego.setEstarSuerte(true);

    }

}
