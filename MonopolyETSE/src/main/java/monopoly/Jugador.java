package monopoly;

import java.util.ArrayList;

public class Jugador {

    /* Atributos */
    private final String nombre;
    private Avatar avatar;
    private double fortuna;
    private ArrayList<Casilla> propiedades;


    /* Constructores */
    public Jugador(String nombre, TipoAvatar tipoAvatar) {

        if (tipoAvatar == null) {
            System.out.println("Tipo de avatar incorrecto");
            System.exit(1);
        }

        this.nombre = nombre;

        //this.avatar = new Avatar(tipoAvatar); falta meterle la casilla, habrá que pasar el Tablero

        this.fortuna = Constantes.DINERO_INICIAL;
        this.propiedades = new ArrayList<>();

    }

    /*Getters y setters*/

    public String getNombre() {
        return nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public double getFortuna() {
        return fortuna;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }

}