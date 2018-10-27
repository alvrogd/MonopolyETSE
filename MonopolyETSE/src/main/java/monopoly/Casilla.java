package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;
    private final Casilla siguienteCasilla;

    private Jugador propietario;
    private boolean estaHipotecada;

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
        this.estaHipotecada = false;

        avataresContenidos = new HashMap<>();

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

    public Casilla getSiguienteCasilla() {
        return siguienteCasilla;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public boolean isEstaHipotecada() {
        return estaHipotecada;
    }

    public void setEstaHipotecada(boolean estaHipotecada) {
        this.estaHipotecada = estaHipotecada;
    }

    public HashMap<String, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public void setAvataresContenidos(HashMap<String, Avatar> avataresContenidos) {
        this.avataresContenidos = avataresContenidos;
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

    public void setEdificiosContenidos(HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos) {
        this.edificiosContenidos = edificiosContenidos;
    }
}
