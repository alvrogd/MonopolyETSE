package aplicacion.excepciones;

public class NumMaximoJugadoresException extends AplicacionException {

    public NumMaximoJugadoresException() {
        super("Has alcanzado el número máximo de jugadores.");
    }

    public NumMaximoJugadoresException(String s) {
        super(s);
    }
}
