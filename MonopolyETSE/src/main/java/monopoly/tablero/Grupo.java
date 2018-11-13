package monopoly.tablero;


import monopoly.Constantes;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;

public class Grupo {

    //Tipo de grupo
    private final TipoGrupo tipo;

    //Precio del grupo, definido como la suma del precio de sus casillas.
    private int precio;

    //ArrayList con las casillas que contiene el grupo
    private final ArrayList<Casilla> casillas;


    /*Constructores*/

    /**
     * Constructor que crea las casillas que contiene además de inicializar todos los atributos de la instancia.
     * @param tipo tipo del grupo
     * @param tablero tablero al que pertenece el grupo
     * @param comprable si sus casillas correspondientes son comprables o no
     * @param casillas un atributo multivalorado en el que se introducirá un ArrayList de Object por casilla, donde este
     *                 almacenará la tupla: fila - posición - nombre de la casilla*/

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
        this.precio = tipo.getPrecioInicial();

        //Comprobación de que las casillas no son null
        this.casillas = new ArrayList<>();
        Casilla aux;

        //Se comprueba que las casillas no sean null y que contengan la información adecuada.
        for(ArrayList<Object> c : casillas){
            if (c == null) {
                System.err.println("Casilla incorrecta.");
                System.exit(1);
            }
            if(c.size() != 3){
                System.err.println("Tupla de posiciones incorrecta.");
                System.exit(1);
            }

            //Para que así casillas pueda determinar su alquiler sabiendo el número de estas que va a haber en su grupo
            this.casillas.add(null);
        }

        int contador = 0;

        for (ArrayList<Object> c : casillas) {

            //Se crea la nueva casilla con el tercer elemento del Array (el nombre), el grupo, si es comprable y su
            //posición en módulo 40, por último se le pasa la banca.
            aux = new Casilla((String)c.get(2), this, comprable,
                    10*(int)c.get(0)+(int)c.get(1), tablero.getBanca());

            //Se mete la casilla en el ArrayList del tablero en su posición correspondiente y en el HashMap.
            tablero.getCasillas().get((int)c.get(0)).set((int)c.get(1),aux);
            tablero.getCasillasTablero().put((String)c.get(2),aux);

            //Por último se añade al ArrayList de Casillas del grupo
            this.casillas.set(contador++, aux);
        }
    }


    /*Getters y Setters*/

    public int getPrecio() {
        return precio;
    }

    public TipoGrupo getTipo() {
        return tipo;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setPrecio(int precio){

        if(precio < 0){
            System.err.println("El precio no puede ser negativo");
            return;
        }

        this.precio = precio;

        int nuevoAlquiler = (int) (Constantes.COEF_ALQUILER * (precio / (double) getCasillas().size()));

        for(Casilla casilla:getCasillas()){
            casilla.setAlquiler(nuevoAlquiler);
        }
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