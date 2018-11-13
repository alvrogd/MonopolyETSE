package monopoly.tablero;

import monopoly.Constantes;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;

    private final int posicionEnTablero;
    private Jugador propietario;
    private boolean hipotecada;

    private HashMap<Character, Avatar> avataresContenidos;

    private int alquiler;
    private boolean comprable;

    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;




    /* Constructores */

    public Casilla(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario) {

        if (grupo == null) {
            System.err.println("Error: grupo no inicializado.");
            System.exit(1);
        }

        if (posicion < 0) {
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

        this.alquiler = (int) (Constantes.COEF_ALQUILER * (grupo.getPrecio() / (double) grupo.getCasillas().size()));

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
        if(propietario == null){
            System.err.println("El propietario referencia a null");
            return;
        }
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

    public HashMap<Character, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public void setAvataresContenidos(HashMap<Character, Avatar> avataresContenidos) {
        if(avataresContenidos == null){
            System.err.println("Avatares referencia a null");
            return;
        }
        this.avataresContenidos = avataresContenidos;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public HashMap<TipoEdificio, ArrayList<Edificio>> getEdificiosContenidos() {
        return edificiosContenidos;
    }

    public void setEdificiosContenidos(HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos) {
        if(edificiosContenidos == null){
            System.err.println("Edificios contenidos referencia a null");
            return;
        }
        this.edificiosContenidos = edificiosContenidos;
    }


    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final Casilla otro = (Casilla) obj;

        // Si los identificadores de sus avatares son el mismo
        if (this.getPosicionEnTablero() != otro.getPosicionEnTablero()) return (false);

        /* Si no se ha cumplido ninguna condición anterior, son el mismo objeto */
        return (true);

    } /* Fin del método equals */

}
