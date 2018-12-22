package monopoly.tablero.jerarquiaCasillas;

import monopoly.jugadores.Avatar;
import monopoly.tablero.Tablero;

import java.util.HashMap;

public abstract class Casilla {

    /* Atributos */
    private final String nombre;
    private final int posicionEnTablero;
    private final Tablero tablero;

    private HashMap<Character, Avatar> avataresContenidos;

    //Atributos de las estadísticas.

    private int frecuencia;

    /* Constructores */

    public Casilla(String nombre, int posicion, Tablero tablero) {

        if (posicion < 0) {
            System.err.println("Error: posición de la casilla en el tablero menor que 0");
            System.exit(1);
        }

        if(nombre == null){
            System.err.println("Error: nombre referencia a null.");
            System.exit(1);
        }

        if(tablero == null){
            System.err.println("Error: nombre referencia a null.");
            System.exit(1);
        }

        this.nombre = nombre;

        this.posicionEnTablero = posicion;

        this.tablero = tablero;

        avataresContenidos = new HashMap<>();

        this.frecuencia = 0;

    }

    /*Getters y setters*/

    public String getNombre() {
        return nombre;
    }

    public int getPosicionEnTablero() {
        return posicionEnTablero;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        if(frecuencia < 0){
            System.err.println("Frecuencia no puede ser negativa.");
            return;
        }
        this.frecuencia = frecuencia;
    }

    public void incrementarFrecuencia(){
        this.frecuencia++;
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

    public Tablero getTablero() {
        return tablero;
    }

    @Override
    public String toString(){

        String salida = "";

        salida += "(*) Casilla: " + getNombre() + "\n";

        return toString();
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
