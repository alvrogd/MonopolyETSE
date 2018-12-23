package monopoly.jugadores.acciones;

import monopoly.jugadores.Jugador;

public class SalirCarcel implements IAccionJugador {

    /* Atributos */

    private Jugador fugitivo;



    /* Contructor */

    public SalirCarcel( Jugador fugitivo ) {

        if( fugitivo == null ) {
            System.err.println( "Fugitivo no inicializado" );
            System.exit( 1 );
        }

        this.fugitivo = fugitivo;
    }



    /* Getters y setters */

    public Jugador getFugitivo() {
        return fugitivo;
    }

    public void setFugitivo(Jugador fugitivo) {

        if( fugitivo == null ) {
            System.err.println( "Fugitivo no inicializado" );
            return;
        }

        this.fugitivo = fugitivo;
    }



    /* Métodos */

    @Override
    public void revertirAccion() {

        // todo se estarán incrementando incorrectamente las estadísticas?
        getFugitivo().getAvatar().caerEnIrACarcel();
    }
}
