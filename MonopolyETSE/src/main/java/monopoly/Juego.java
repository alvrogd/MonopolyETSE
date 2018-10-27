package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego {

    private Jugador turno;
    private HashMap<String,Jugador> jugadores;
    private ArrayList<String> nombresJugadores;
    private Tablero tablero;

    public Juego(ArrayList<ArrayList<Casilla>> casillas) {

        if (casillas == null) {
            System.out.println("Casillas hace referencia a null");
            System.exit(1);
        }

        //Se comprueba si las casillas están bien inicializadas

        for (ArrayList<Casilla> array : casillas) {
            if (array == null) {
                System.out.println("Casillas hace referencia a null");
                System.exit(1);
            }
            for(Casilla c: array){
                if(c == null){
                    System.out.println("Casilla hace referencia a null");
                    System.exit(1);
                }
            }
        }

        turno = new Jugador("Banca",TipoAvatar.banca);
        jugadores = new HashMap<>();
        nombresJugadores = new ArrayList<>();
        tablero = new Tablero(casillas);

    }

    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }
}
