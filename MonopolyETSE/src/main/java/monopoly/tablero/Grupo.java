package monopoly.tablero;


import monopoly.jugadores.Jugador;

import java.util.ArrayList;

public class Grupo {

    private final TipoGrupo tipo;
    private int precio;
    private final ArrayList<Casilla> casillas;

    //Se le pasa un arrayList que contiene la tupla fila/posicion/nombreCasilla
    public Grupo(TipoGrupo tipo, Tablero tablero, boolean comprable, ArrayList<Object>... casillas) {

        //Comprobación del tipo de grupo
        if (tipo == null) {
            System.err.println("Tipo referencia a null");
            System.exit(1);
        }
        if (tablero == null) {
            System.err.println("Tablero referencia a null");
            System.exit(1);
        }
        if(casillas == null) {
            System.err.println("Posiciones referencia a null");
            System.exit(1);
        }

        this.tipo = tipo;
        precio = tipo.getPrecioInicial();

        //Comprobación de que las casillas no son null
        this.casillas = new ArrayList<>();
        Casilla aux;
        for (ArrayList<Object> c : casillas) {
            if (c == null) {
                System.err.println("Casilla incorrecta.");
                System.exit(1);
            }
            if(c.size() != 3){
                System.err.println("Tupla de posiciones incorrecta.");
                System.exit(1);
            }

            aux = new Casilla((String)c.get(2), this, comprable, 10*(int)c.get(0)+(int)c.get(1), tablero.getBanca());

            tablero.getCasillas().get((int)c.get(0)).set((int)c.get(1),aux);
            tablero.getCasillasTablero().put((String)c.get(2),aux);

            this.casillas.add(aux);
        }
    }

    public int getPrecio() {
        return precio;
    }

    public TipoGrupo getTipo() {
        return tipo;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Grupo)) {
            return false;
        } else if (this == o) {
            return true;
        } else if (tipo.equals(((Grupo) o).tipo)) {
            return true;
        }
        return false;
    }

}