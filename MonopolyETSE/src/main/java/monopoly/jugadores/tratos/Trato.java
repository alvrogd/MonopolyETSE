package monopoly.jugadores.tratos;

import monopoly.jugadores.Jugador;

public abstract class Trato {

    /* Atributos */

    private final Jugador emisor;
    private final Jugador receptor;



    /* Constructor */

    public Trato( Jugador emisor, Jugador receptor ) {

        if( emisor == null ) {
            System.err.println( "Emisor no inicializado");
            System.exit( 1 );
        }

        if( receptor == null ) {
            System.err.println( "Receptor no inicializado");
            System.exit( 1 );
        }

        this.emisor = emisor;
        this.receptor = receptor;
    }



    /* Getters y setters */

    public Jugador getEmisor() {
        return emisor;
    }


    public Jugador getReceptor() {
        return receptor;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     */
    public abstract void aceptar();
}
