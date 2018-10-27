package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {

    /*Atributos*/
    private Dado dado;
    private ArrayList<ArrayList<Casilla>> casillas;
    private HashMap<String, Avatar> avataresContenidos;
    private HashMap<String, Casilla> casillasTablero;

    /*Constructores*/
    public Tablero(ArrayList<ArrayList<Casilla>> casillas) {

        //No tengo muy claro si se debería comprobar...

        if (casillas == null) {
            System.out.println("Casillas hace referencia a null");
            System.exit(1);
        }

        casillasTablero = new HashMap<String, Casilla>();

        //Se comprueba si las casillas están bien inicializadas y en caso de que así sean se añade al HashMap de casilla

        for (ArrayList<Casilla> array : casillas) {
            if (array == null) {
                System.out.println("Casillas hace referencia a null");
                System.exit(1);
            }
            for (Casilla c : array) {
                if (c == null) {
                    System.out.println("Casilla hace referencia a null");
                    System.exit(1);
                }

                casillasTablero.put(c.getNombre(), c);
            }
        }

        this.casillas = casillas;
        avataresContenidos = new HashMap<>();
        dado = new Dado();
    }

    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return casillas;
    }

    public HashMap<String, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public HashMap<String, Casilla> getCasillasTablero() {
        return casillasTablero;
    }

}