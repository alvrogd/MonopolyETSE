package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.acciones.TransferenciaMonetaria;
import monopoly.tablero.Tablero;

public class Salida extends Especial{

    public Salida(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    public void ejecutarAccion(Jugador jugador, boolean importeSalidaEstandar){

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        int importe = (importeSalidaEstandar ? Constantes.DINERO_SALIDA : Constantes.DINERO_SALIDA_CARTA);

        jugador.setFortuna(jugador.getFortuna() + importe);
        jugador.incrementarPasarPorCasillaDeSalida(importe);
        Output.respuesta("Has cobrado el importe de la casilla de salida.");

        // Se registra el pago obtenido
        jugador.getAcciones().add(new TransferenciaMonetaria(importe, getTablero().getBanca(), jugador));

    }

    public void ejecutarAccion(Jugador jugador){

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        int importe = (Constantes.DINERO_SALIDA);

        jugador.setFortuna(jugador.getFortuna() + importe);
        jugador.incrementarPasarPorCasillaDeSalida(importe);
        Output.respuesta("Has cobrado el importe de la casilla de salida.");

        // Se registra el pago obtenido
        jugador.getAcciones().add(new TransferenciaMonetaria(importe, getTablero().getBanca(), jugador));

    }

}
