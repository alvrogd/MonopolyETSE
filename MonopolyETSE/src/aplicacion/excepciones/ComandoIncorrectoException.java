package aplicacion.excepciones;

public class ComandoIncorrectoException extends AplicacionException {

    private String nombreComando;
    private String mensaje;
    private String sugerencia;
    private boolean haySugerencia;

    public ComandoIncorrectoException(String nombreComando) {

        super("Comando «" + nombreComando + "» incorrecto.");
        this.nombreComando = nombreComando;
        this.mensaje = "";
        this.sugerencia = "";
        this.haySugerencia = false;

    }

    public ComandoIncorrectoException(String nombreComando, String mensaje) {

        super("Comando «" + nombreComando + "» incorrecto: "+mensaje);
        this.nombreComando = nombreComando;
        this.mensaje = mensaje;
        this.sugerencia = "";
        this.haySugerencia = false;

    }

    public ComandoIncorrectoException(){

        super("Comando incorrecto.");

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

    public String getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(String sugerencia) {
        this.haySugerencia = true;
        this.sugerencia = sugerencia;
    }

    public boolean isHaySugerencia() {
        return haySugerencia;
    }
}
