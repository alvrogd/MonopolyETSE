package monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Juego {

    /* Atributos */
    private Jugador turno;
    private HashMap<String, Jugador> jugadores;
    private ArrayList<String> nombresJugadores;
    private Jugador Banca;
    private Tablero tablero;
    private Iterator iterador;
    private boolean iniciado;

    /* Constructores */
    public Juego(ArrayList<ArrayList<Casilla>> casillas) {

        if (casillas == null) {
            System.out.println("Casillas hace referencia a null");
            System.exit(1);
        }

        //Se comprueba si las casillas están bien inicializadas

        for (ArrayList<Casilla> array : casillas) {
            if (array == null) {
                System.out.println("Casillas hace referencia a null");
                System.exit(1);
            }
            for (Casilla c : array) {
                if (c == null) {
                    System.out.println("Casilla hace referencia a null");
                    System.exit(1);
                }
            }
        }

        Banca = new Jugador("Banca", TipoAvatar.banca);
        turno = Banca;
        jugadores = new HashMap<>();
        nombresJugadores = new ArrayList<>();
        tablero = new Tablero(casillas);
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
        return Banca;
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
        tablero.getAvataresContenidos().put(String.valueOf(jugador.getAvatar().getIdentificador()), jugador.getAvatar());

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
