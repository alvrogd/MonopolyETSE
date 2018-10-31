package monopoly.tablero;

import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;

    private final int posicionEnTablero;
    private Jugador propietario;
    private boolean hipotecada;

    private HashMap<String, Avatar> avataresContenidos;

    private double alquiler;
    private boolean comprable;

    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;




    /* Constructores */

    public Casilla(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario) {

        if (grupo == null) {
            System.err.println("Error: grupo no inicializado.");
            System.exit(1);
        }

        if( posicion < 0 ) {
            System.err.println("Error: posición de la casilla en el tablero menor que 0");
            System.exit(1);
        }

        if (propietario == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.nombre = nombre;
        this.grupo = grupo;
        this.posicionEnTablero = posicion;

        this.comprable = comprable;

        this.propietario = propietario;
        this.hipotecada = false;

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

    public int getPosicionEnTablero() {
        return posicionEnTablero;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public boolean isComprable() {
        return comprable;
    }

    public void setComprable(boolean comprable) {
        this.comprable = comprable;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
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
