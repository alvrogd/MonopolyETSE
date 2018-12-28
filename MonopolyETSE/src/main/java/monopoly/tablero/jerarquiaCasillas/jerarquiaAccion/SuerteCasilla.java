package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.Aplicacion;
import monopoly.Constantes;
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
            ImposibleMoverseException, EdificiosSolarException {

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        int opcion = Aplicacion.consola.leer("¡Carta de suerte! Introduzca un número del 1 al " +
                Constantes.NUM_CARTAS_SUERTE, true);

        Carta carta = getTablero().getJuego().barajarSuerte(opcion%Constantes.NUM_CARTAS_SUERTE);

        carta.accion();
    }

}
