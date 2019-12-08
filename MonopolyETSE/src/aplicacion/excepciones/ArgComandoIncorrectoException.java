package aplicacion.excepciones;

public class ArgComandoIncorrectoException extends AplicacionException {

    private String nombreComando;
    private String mensaje;
    private String sugerencia;
    private boolean haySugerencia;

    public ArgComandoIncorrectoException(String nombreComando) {

        super("Argumentos del comando «" + nombreComando + "» incorrectos.");
        this.nombreComando = nombreComando;
        this.mensaje = "";
        this.sugerencia = "";
        this.haySugerencia = false;

    }

    public ArgComandoIncorrectoException(String nombreComando, String mensaje) {

        super("Argumentos del comando «" + nombreComando + "» incorrectos: "+mensaje);
        this.nombreComando = nombreComando;
        this.mensaje = mensaje;
        this.sugerencia = "";
        this.haySugerencia = false;

    }

    public ArgComandoIncorrectoException(){

        super("Argumentos incorrectos.");

        this.nombreComando = "";
        this.mensaje = "";
        this.sugerencia = "";
        this.haySugerencia = false;

    }

    public String getNombreComando() {
        return nombreComando;
    }

    public void setNombreComando(String nombreComando) {
        this.nombreComando = nombreComando;
    }

    public boolean isHaySugerencia() {
        return haySugerencia;
    }

    public String getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(String sugerencia){
        this.haySugerencia = true;
        this.sugerencia = sugerencia;
    }
}
