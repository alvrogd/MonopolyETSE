package monopoly.jugadores.excepciones;

public class NumMaximoJugadoresException extends Exception{

    public NumMaximoJugadoresException() {
        super("Has alcanzado el número máximo de jugadores.");
    }

    public NumMaximoJugadoresException(String s) {
        super(s);
    }
}
