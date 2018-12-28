package monopoly.jugadores.excepciones;

public class NoSuficientesJugadoresException extends Exception{

    public NoSuficientesJugadoresException() {
        super("No hay suficientes jugadores para empezar el juego.");
    }

    public NoSuficientesJugadoresException(String s) {
        super(s);
    }
}
