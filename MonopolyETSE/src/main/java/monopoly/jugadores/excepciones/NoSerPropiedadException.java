package monopoly.jugadores.excepciones;

public class NoSerPropiedadException extends ArgComandoIncorrectoException{

    public NoSerPropiedadException(String nombreComando) {
        super(nombreComando, "la casilla introducida no es una propiedad.");
    }

    public NoSerPropiedadException(String nombreComando, String idAvatar) {
        super(nombreComando, "la casilla " + idAvatar + " no es una propiedad.");
    }

}
