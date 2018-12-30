package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.acciones.TransferenciaMonetaria;
import monopoly.jugadores.excepciones.EstarBancarrotaException;
import monopoly.jugadores.excepciones.ImposibleCambiarModoException;
import monopoly.jugadores.excepciones.ImposibleMoverseException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;

import java.util.Set;

public class Parking extends Especial{

    private int dinero;

    public Parking(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);
        dinero = 0;

    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero){
        if(dinero < 0){
            System.err.println("Dinero negativo.");
            System.exit(-1);
        }
        this.dinero = dinero;
    }

    public void incrementarDinero(int aumento){
        setDinero(getDinero()+aumento);
    }

    public void ejecutarAccion(Jugador jugador){

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        jugador.setFortuna(jugador.getFortuna() + getDinero());

        Output.respuesta("¡Has cobrado el valor acumulado por los impuestos!");
        // Y se resetea el valor del "alquiler" del parking

        jugador.incrementarPremiosInversionesOBote(getDinero());

        // Se registra el pago obtenido
        jugador.getAcciones().add(new TransferenciaMonetaria(getDinero(), false, getTablero().getBanca(), jugador));

        setDinero(0);

    }

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "        -> Bote:" + getDinero() + "K €\n\n";

        StringBuilder jugadoresContenidos = new StringBuilder("        -> Jugadores: {");

        Set<Character> avatares = getAvataresContenidos().keySet();

        Avatar avatarAux;

        boolean flag = false;

        for(Character avatar : avatares){

            avatarAux = getAvataresContenidos().get(avatar);
            jugadoresContenidos.append(avatarAux.getJugador().getNombre());
            jugadoresContenidos.append(", ");

        }

        int tam = jugadoresContenidos.toString().length();

        jugadoresContenidos.replace(tam, tam, "}");

        salida += jugadoresContenidos.toString() + "\n";

        return salida;
    }

}
