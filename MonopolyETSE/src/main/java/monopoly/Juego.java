package monopoly;

import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Juego {

    /* Atributos */
    private Jugador turno;
    private HashMap<String, Jugador> jugadores;
    private ArrayList<String> nombresJugadores;
    private Jugador banca;
    private Tablero tablero;
    private Iterator iterador;
    private int vueltasMin;
    private boolean iniciado;
    private boolean seHaIncrementado;
    private boolean haLanzadoDados;

    /* Constructores */
    public Juego() {

        banca = new Jugador("banca");
        turno = banca;
        jugadores = new HashMap<>();
        nombresJugadores = new ArrayList<>();
        tablero = new Tablero(banca, this);
        iniciado = false;
        vueltasMin = 0;
        seHaIncrementado = false;
        haLanzadoDados = false;

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

    public Jugador getJugador(String jugador) {
        return (jugadores.get(jugador));
    }

    public Jugador getBanca() {
        return banca;
    }

    public Jugador getTurno() {
        return turno;
    }

    public boolean isHaLanzadoDados() {
        return haLanzadoDados;
    }

    public void setHaLanzadoDados(boolean haLanzadoDados) {
        this.haLanzadoDados = haLanzadoDados;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    /* Setters */
    public void addJugador(Jugador jugador) {

        if (jugador == null) {
            System.out.println("Jugador referencia a null");
            System.exit(1);
        }

        jugadores.put(jugador.getNombre(), jugador);
        nombresJugadores.add(jugador.getNombre());
        tablero.getAvataresContenidos().put(jugador.getAvatar().getIdentificador(), jugador.getAvatar());
        //Para meter los avatares en el tablero, para cuando esté listo Jugador
    }

    /* Métodos */

    public void iniciarJuego() {
        if (jugadores.isEmpty()) {
            System.err.println("No ha introducido ningún jugador");
            return;
        }
        if (!iniciado) {
            iniciado = true;
            iterador = nombresJugadores.iterator();
            turno = jugadores.get(iterador.next());
        } else {
            System.err.println("El juego ya está iniciado");
            return;
        }
    }

    public void finalizarTurno() {

        if (iniciado) {

            if (iterador == null) {
                System.out.println("No se ha añadido ningún jugador.");
                System.exit(1);
            }

            getTurno().setTiradasEnTurno(0);

            if (iterador.hasNext())
                turno = jugadores.get(iterador.next());
            else {
                iterador = nombresJugadores.iterator();
                turno = jugadores.get(iterador.next());
            }

            haLanzadoDados = false;

        } else {

            System.out.println("Juego no iniciado.");
            return;
        }

    }

    public void actualizarVueltas() {

        if (!iniciado) {
            System.err.println("Juego no iniciado.");
            return;
        }

        ArrayList<String> jugadores = getNombresJugadores();

        int min = getJugadores().get(jugadores.get(0)).getAvatar().getVueltas();
        int auxVueltas;

        for (String jugador : jugadores) {

            auxVueltas = getJugadores().get(jugador).getAvatar().getVueltas();

            if (min > auxVueltas) {

                min = auxVueltas;

            }

        }

        vueltasMin = min;

        //En el caso de que todos los avatares hayan recorrido ya 4 vueltas y no se haya incrementado ya antes el precio
        //de los solares

        if (vueltasMin == 4 && !seHaIncrementado) {

            //Se miran todas las casillas del tablero y en caso de que sean comprables y no sean ni transportes ni
            //servicios se incrementa el precio del grupo en Constantes.INCREMENTO_VUELTAS

            for (ArrayList<Casilla> fila : tablero.getCasillas()) {
                for (Casilla casilla : fila) {
                    if (casilla.isComprable()) {

                        if (casilla.getGrupo().getTipo() != TipoGrupo.transporte &&
                                casilla.getGrupo().getTipo() != TipoGrupo.servicios) {

                            casilla.getGrupo().setPrecio(
                                    (int) ((1.0 + Constantes.INCREMENTO_VUELTAS) * casilla.getGrupo().getPrecio()));

                        }
                    }
                }
            }

        }

    }
}
