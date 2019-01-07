package aplicacion.excepciones;

public class NoSuficientesJugadoresException extends AplicacionException {

    public NoSuficientesJugadoresException() {
        super("No hay suficientes jugadores para empezar el juego.");
    }

    public NoSuficientesJugadoresException(String s) {
        super(s);
    }
}
