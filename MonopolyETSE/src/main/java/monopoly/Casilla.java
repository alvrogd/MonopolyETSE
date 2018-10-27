package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;
    private final Casilla siguienteCasilla;

    private Jugador propietario;
    private HashMap<String, Avatar> avataresContenidos;

    private double alquiler;
    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;




    /* Constructores */

    public Casilla(String nombre, Grupo grupo, Casilla siguienteCasilla, Jugador propietario) {

        if (grupo == null) {
            System.err.println("Error: grupo no inicializado.");
            System.exit(1);
        }

        if( siguienteCasilla == null ){
            System.err.println("Error: siguiente casilla no inicializada");
            System.exit(1);
        }

        if (propietario == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.nombre = nombre;
        this.grupo = grupo;
        this.siguienteCasilla = siguienteCasilla;

        this.propietario = propietario;
        avataresContenidos = new HashMap<>();

        // Aquí no se realiza la comprobación del tipoGrupo porque ya se hace en el constructor de Grupo, y previamente
        // se ha comprobado si grupo es null
        this.alquiler = grupo.getTipo().getPrecioInicial();

        edificiosContenidos = new HashMap<>();

    }

    /*Getters y setters*/

    public String getNombre() {
        return nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public Casilla getSiguienteCasilla() { return siguienteCasilla; }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public HashMap<String, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public double getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public HashMap<TipoEdificio, ArrayList<Edificio>> getEdificiosContenidos() {
        return edificiosContenidos;
    }
}
