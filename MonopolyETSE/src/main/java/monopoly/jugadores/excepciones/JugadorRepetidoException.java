package monopoly.jugadores.excepciones;

public class JugadorRepetidoException extends Exception{

    public JugadorRepetidoException() {
        super("Ese jugador ya pertenece al juego.");
    }

    public JugadorRepetidoException(String s) {
        super(s);
    }
}
