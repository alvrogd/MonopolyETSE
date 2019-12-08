package aplicacion.excepciones;

public class SubcomandoIncorrectoException extends ArgComandoIncorrectoException {

    public SubcomandoIncorrectoException(String nombreComando, String nombreSubComando) {

        super(nombreComando, "error en el subcomando «" + nombreSubComando + "».");

    }

    public SubcomandoIncorrectoException(String nombreComando, String nombreSubComando, String mensaje) {

        super(nombreComando, "error en el subcomando «" + nombreSubComando + "»: " +mensaje);

    }


}
