package monopoly.jugadores.excepciones;

public class SeHaCompradoCasillaTurnoException extends Exception{

    public SeHaCompradoCasillaTurnoException() {
        super("Ya has comprado una casilla en este turno.");
    }

    public SeHaCompradoCasillaTurnoException(String s) {
        super(s);
    }
}
