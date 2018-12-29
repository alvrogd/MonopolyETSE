package aplicacion.excepciones;

public class JugadorRepetidoException extends AplicacionException {

    public JugadorRepetidoException() {
        super("Ese jugador ya pertenece al juego.");
    }

    public JugadorRepetidoException(String s) {
        super(s);
    }
}
