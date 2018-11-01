package monopoly;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.Tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Juego {

    /* Atributos */
    private Jugador turno;
    private HashMap<String, Jugador> jugadores;
    private ArrayList<String> nombresJugadores;
    private Jugador banca;
    private Tablero tablero;
    private Iterator iterador;
    private boolean iniciado;

    /* Constructores */
    public Juego() {

        //banca = new Jugador("banca", TipoAvatar.banca);
        //turno = banca;
        jugadores = new HashMap<>();
        nombresJugadores = new ArrayList<>();
        //tablero = new Tablero(banca);  // todo comentado porque es imposible compilar con errores para probar algo
        iniciado = false;

    }

    /* Getters */
    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<String> getNombresJugadores() {
        return nombresJugadores;
    }

    public Jugador getJugador(Jugador jugador) {
        return (jugadores.get(jugador.getNombre()));
    }

    public Jugador getBanca() {
        return banca;
    }

    public Jugador getTurno(){
        return turno;
    }

    /* Setters */
    public void addJugador(Jugador jugador) {

        if (jugador == null) {
            System.out.println("Jugador referencia a null");
            System.exit(1);
        }

        jugadores.put(jugador.getNombre(), jugador);
        nombresJugadores.add(jugador.getNombre());
        tablero.getAvataresContenidos().put((Character)jugador.getAvatar().getIdentificador(), jugador.getAvatar());
        //Para meter los avatares en el tablero, para cuando esté listo Jugador
    }




    /* Métodos */

    public void iniciarJuego() {
        if (!iniciado) {
            iniciado = true;
            iterador = nombresJugadores.iterator();
            turno = jugadores.get(iterador.next());
        } else {
            System.out.println("El juego ya está iniciado");
            return;
        }
    }

    public void finalizarTurno() {

        if (iniciado) {

            if (iterador == null) {
                System.out.println("No se ha añadido ningún jugador.");
                System.exit(1);
            }

            if (iterador.hasNext())
                turno = jugadores.get(iterador.next());
            else {
                iterador = nombresJugadores.iterator();
                turno = jugadores.get(iterador.next());
            }

        } else {

            System.out.println("Juego no iniciado.");
            return;
        }

    }
}
