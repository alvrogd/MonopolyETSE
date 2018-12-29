package monopoly.jugadores.excepciones;

public class JuegoIniciadoException extends Exception{

    public JuegoIniciadoException() {
        super("El juego ya está iniciado.");
    }

    public JuegoIniciadoException(String mensaje) {
        super("El juego ya está iniciado. "+mensaje);
    }

}
