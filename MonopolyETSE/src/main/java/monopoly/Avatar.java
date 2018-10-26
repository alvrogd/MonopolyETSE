package monopoly;

public class Avatar {

    /* Atributos */
    private boolean haEstadoCarcel;    // Se resetea a false al completar una vuelta
    private boolean estaCarcel;
    private int vueltas;
    private Casilla posicion;
    private final char identificador;
    private final TipoAvatar tipo;
    private boolean movimientoEstandar;


    /* Constructores */
    public Avatar( Casilla posicionInicial, TipoAvatar tipo ) {

        if( posicionInicial == null )
        {
            System.out.println( "La casilla de posición inicial referencia a null" );
            System.exit( 1 );
        }

        if( tipo == null )
        {
            System.out.println( "El tipo de avatar referencia a null" );
            System.exit( 1 );
        }


    }
}
