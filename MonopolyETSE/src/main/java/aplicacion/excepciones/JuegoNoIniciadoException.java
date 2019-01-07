package aplicacion.excepciones;

public class JuegoNoIniciadoException extends AplicacionException {

    public JuegoNoIniciadoException() {
        super("El juego no está iniciado.");
    }

    public JuegoNoIniciadoException(String mensaje) {
        super("El juego no está iniciado. "+mensaje);
    }

}
