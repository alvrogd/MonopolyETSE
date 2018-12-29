package aplicacion.excepciones;

public class JuegoIniciadoException extends AplicacionException {

    public JuegoIniciadoException() {
        super("El juego ya está iniciado.");
    }

    public JuegoIniciadoException(String mensaje) {
        super("El juego ya está iniciado. "+mensaje);
    }

}
