package monopoly.jugadores.excepciones;

public class JuegoNoIniciadoException extends Exception{

    public JuegoNoIniciadoException() {
        super("El juego no está iniciado.");
    }

    public JuegoNoIniciadoException(String mensaje) {
        super("El juego no está iniciado. "+mensaje);
    }

}
