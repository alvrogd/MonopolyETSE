package aplicacion.excepciones;

public class NoExisteCasillaException extends ArgComandoIncorrectoException {

    public NoExisteCasillaException(String nombreComando) {
        super(nombreComando, "la casilla introducida no existe.");
    }

    public NoExisteCasillaException(String nombreComando, String nombreCasilla) {
        super(nombreComando, "la casilla " + nombreCasilla + " no existe.");
    }
}
