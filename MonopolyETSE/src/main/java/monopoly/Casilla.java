package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;

    private Jugador propietario;
    private HashMap<String, Avatar> avataresContenidos;

    private double alquiler;
    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;




    /* Constructores */

    public Casilla(String nombre, Grupo grupo, Jugador propietario) {

        TipoGrupo tipoGrupo;

        if (grupo == null) {
            System.out.println("Grupo no inicializado.");
            System.exit(1);
        }

        if (propietario == null) {
            System.err.println("Jugador no inicializado.");
        }

        this.nombre = nombre;
        this.grupo = grupo;

        this.propietario = propietario;
        avataresContenidos = new HashMap<>();

        tipoGrupo = grupo.getTipo(); //Aquí no se realiza la comprobación del tipoGrupo porque ya se hace en el
        //constructor de Grupo, y previamente se ha comprobado si grupo es null.

        this.alquiler = tipoGrupo.getPrecioInicial();

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
