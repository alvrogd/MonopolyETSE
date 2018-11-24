package monopoly.tablero.cartas;

public class Carta {

    /* Atributos */
    private final TipoAccion tipoAccion;
    private final Enum accion;


    /* Constructor */
    public Carta( TipoAccion tipoAccion, Enum accion ) {

        if( tipoAccion == null ) {
            System.err.println( "Tipo de acción no inicializado" );
            System.exit(1);
        }

        if( accion == null ) {
            System.err.println("Acción no inicializada" );
            System.exit(1);
        }

        this.tipoAccion = tipoAccion;
        this.accion = accion;

    }


    /* Métodos */

    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }

    public Enum getAccion() {
        return accion;
    }

}
