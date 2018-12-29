package monopoly.jugadores.excepciones;

public class NoSerSolarException extends ArgComandoIncorrectoException{

    public NoSerSolarException(String nombreComando) {
        super(nombreComando, "la casilla introducida no es un solar.");
    }

    public NoSerSolarException(String nombreComando, String idAvatar) {
        super(nombreComando, "la casilla " + idAvatar + " no es un solar.");
    }

}
