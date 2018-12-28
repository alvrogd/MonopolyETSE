package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.Aplicacion;
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.Tablero;
import monopoly.tablero.cartas.Carta;

public class ComunidadCasilla extends Accion{

    public ComunidadCasilla(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    @Override
    public void ejecutarAccion(Jugador jugador) throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException, EdificiosSolarException {

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        int opcion = Aplicacion.consola.leer("¡Carta de comunidad! Introduzca un número del 1 al " +
                Constantes.NUM_CARTAS_COMUNIDAD, true);

        Carta carta = getTablero().getJuego().barajarComunidad(opcion%Constantes.NUM_CARTAS_COMUNIDAD);

        carta.accion();
    }

}
