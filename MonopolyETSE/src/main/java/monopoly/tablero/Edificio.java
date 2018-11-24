package monopoly.tablero;

import monopoly.tablero.TipoEdificio;
import monopoly.tablero.TipoGrupo;

public class Edificio {

    /* Atributos */
    private final TipoEdificio tipo;
    private final int precioCompra;
    private final String id;

    /* Constructor */

    public Edificio(Tablero tablero, TipoEdificio tipoEdificio, TipoGrupo grupo) {

        StringBuilder newId = new StringBuilder();
        Integer numEdificio;

        //Comprobación de TipoGrupo
        if (tipoEdificio == null) {
            System.out.println("Error en el tipo de edificio.");
            System.exit(1);
        }

        if (grupo == null) {
            System.out.println("Error en el tipo de grupo");
            System.exit(1);
        }

        if (tablero == null) {
            System.out.println("Tablero referencia a null");
            System.exit(1);
        }

        numEdificio = tablero.getNumEdificios().get(tipoEdificio);

        if(numEdificio == null){

            tablero.getNumEdificios().put(tipoEdificio, 1);

        }

        newId.append(tipoEdificio.getNombre()+"-"+ numEdificio.toString());

        this.id = newId.toString();

        tipo = tipoEdificio;

        tablero.getNumEdificios().put(tipoEdificio, ++numEdificio);

        precioCompra = (int) (grupo.getPrecioInicial() * tipoEdificio.getCompra());

    }

    /* Getters */
    public TipoEdificio getTipo() {
        return tipo;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public String getId() {
        return id;
    }

    /**
     * Método estático para poder calcular el precio de compra de un edificio en concreto sin necesidad de instanciarlo.
     * @param tipoEdificio tipo del edificio del que se quiere calcular el precio
     * @param tipoGrupo tipo del grupo en el que se quiere calcular el precio del edificio
     * @return
     */
    public static Integer calcularPrecioCompra(TipoEdificio tipoEdificio, TipoGrupo tipoGrupo){

        if(tipoEdificio == null){
            System.err.println("Tipo de edificio referencia a null");
            return null;
        }
        if(tipoGrupo == null){
            System.err.println("Tipo de grupo referencia a null");
            return null;
        }

        return((int)(tipoGrupo.getPrecioInicial() * tipoEdificio.getCompra()));
    }

}
