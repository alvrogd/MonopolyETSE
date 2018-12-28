package monopoly.jugadores.excepciones;

public class ArgComandoIncorrectoException extends Exception{

    private String nombreComando;
    private String mensaje;

    public ArgComandoIncorrectoException(String nombreComando) {

        super("Argumentos del comando «" + nombreComando + "» incorrectos.");
        this.nombreComando = nombreComando;
        this.mensaje = "";

    }

    public ArgComandoIncorrectoException(String nombreComando, String mensaje) {

        super("Argumentos del comando «" + nombreComando + "» incorrectos: "+mensaje);
        this.nombreComando = nombreComando;
        this.mensaje = mensaje;

    }

    public ArgComandoIncorrectoException(){

        super("Argumentos incorrectos.");

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
