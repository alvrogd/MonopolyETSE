package monopoly.tablero.jerarquiaCasillas;


import monopoly.Constantes;
import monopoly.jugadores.Participante;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;

public class Grupo {

    //Tipo de grupo
    private final TipoGrupo tipo;

    //Precio del grupo, definido como la suma del precio de sus solares.
    private int precio;

    //ArrayList con las solares que contiene el grupo
    private final ArrayList<Propiedad> propiedades;

    private Tablero tablero;


    /*Constructores*/

    public Grupo(TipoGrupo tipo) {
        this.tipo = tipo;
        this.propiedades = new ArrayList<>();
    }

    /**
     * Constructor que crea las solares que contiene además de inicializar todos los atributos de la instancia.
     * @param tipo tipo del grupo
     * @param tablero tablero al que pertenece el grupo
     * @param comprable si sus solares correspondientes son comprables o no
     * @param propiedades un atributo multivalorado en el que se introducirá un ArrayList de Object por casilla, donde este
     *                 almacenará la tupla: fila - posición - nombre de la casilla*/

    public Grupo(TipoGrupo tipo, Tablero tablero, boolean comprable, ArrayList<Object>... propiedades) {

        //Comprobación del tipo de grupo
        if (tipo == null) {
            System.err.println("Tipo referencia a null");
            System.exit(1);
        }
        if (tablero == null) {
            System.err.println("Tablero referencia a null");
            System.exit(1);
        }
        if(propiedades == null) {
            System.err.println("Posiciones referencia a null");
            System.exit(1);
        }

        this.tipo = tipo;
        this.precio = tipo.getPrecioInicial();

        //Comprobación de que las propiedades no son null
        this.propiedades = new ArrayList<>();

        //Se comprueba que las propiedades no sean null y que contengan la información adecuada.
        for(ArrayList<Object> c : propiedades){

            if (c == null) {
                System.err.println("Casilla incorrecta.");
                System.exit(1);
            }
            if(c.size() != 3){
                System.err.println("Tupla de posiciones incorrecta.");
                System.exit(1);
            }

            //Para que así propiedades pueda determinar su alquiler sabiendo el número de estas que va a haber en su grupo
            this.propiedades.add(null);
        }

        int contador = 0;

        this.tablero = tablero;

        Propiedad aux = null;

        for (ArrayList<Object> c : propiedades) {

            //Se crea la nueva casilla con el tercer elemento del Array (el nombre), el grupo, si es comprable y su
            //posición en módulo 40, por último se le pasa la banca.

            if(tipo.getTipoCasilla().equals("solar")) {

                aux = (Propiedad) new Solar((String) c.get(2), this, comprable,
                        10 * (int) c.get(0) + (int) c.get(1), tablero.getBanca(), tablero);

            } else if(tipo.getTipoCasilla().equals("servicio")){

                aux = (Propiedad) new Servicio((String) c.get(2), this, comprable,
                        10 * (int) c.get(0) + (int) c.get(1), tablero.getBanca(), tablero);

            } else if(tipo.getTipoCasilla().equals("transporte")){

                aux = (Propiedad) new Transporte((String) c.get(2), this, comprable,
                        10 * (int) c.get(0) + (int) c.get(1), tablero.getBanca(), tablero);

            } else {

                System.err.println("Tipo de grupo incorrecto.");
                System.exit(-1);

            }

            //Se mete la casilla en el ArrayList del tablero en su posición correspondiente y en el HashMap.
            tablero.getCasillas().get((int)c.get(0)).set((int)c.get(1),aux);
            tablero.getCasillasTablero().put((String)c.get(2),aux);

            //Por último se añade al ArrayList de Casillas del grupo
            this.propiedades.set(contador++, aux);
        }
    }

    /*Getters y Setters*/

    public int getPrecio() {
        return precio;
    }

    public TipoGrupo getTipo() {
        return tipo;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setPrecio(int precio){

        if(precio < 0){
            System.err.println("El precio no puede ser negativo");
            return;
        }

        this.precio = precio;

        int nuevoAlquiler = (int) (Constantes.COEF_ALQUILER * (precio / (double) getPropiedades().size()));

        for(Propiedad propiedad:getPropiedades()){
            propiedad.setAlquiler(nuevoAlquiler);
        }
    }

    /**
     * Función para calcular la rentabilidad del grupo
     */
    public int calcularRentabilidad(){

        ArrayList<Propiedad> propiedades = getPropiedades();
        int rentabilidad = 0;

        for(Propiedad solar : propiedades){

            rentabilidad += solar.getRentabilidad();

        }

        return rentabilidad;

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