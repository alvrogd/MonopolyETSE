package aplicacion.excepciones;

import aplicacion.excepciones.ArgComandoIncorrectoException;

public class NoExisteJugadorException extends ArgComandoIncorrectoException {

    public NoExisteJugadorException(String nombreComando) {
        super(nombreComando, "el jugador introducido no existe.");
    }

    public NoExisteJugadorException(String nombreComando, String nombreJugador) {
        super(nombreComando, "el jugador " + nombreJugador + " no existe.");
    }

}

