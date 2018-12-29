package monopoly.jugadores.excepciones;

public class ComandoIncorrectoException extends Exception{

    private String nombreComando;
    private String mensaje;

    public ComandoIncorrectoException(String nombreComando) {

        super("Comando «" + nombreComando + "» incorrecto.");
        this.nombreComando = nombreComando;
        this.mensaje = "";

    }

    public ComandoIncorrectoException(String nombreComando, String mensaje) {

        super("Comando «" + nombreComando + "» incorrecto: "+mensaje);
        this.nombreComando = nombreComando;
        this.mensaje = mensaje;

    }

    public ComandoIncorrectoException(){

        super("Comando incorrecto.");

        this.nombreComando = "";
        this.mensaje = "";

    }

    public String getNombreComando() {
        return nombreComando;
    }

    public void setNombreComando(String nombreComando) {
        this.nombreComando = nombreComando;
    }
}
