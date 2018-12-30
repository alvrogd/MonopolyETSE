package monopoly.jugadores.acciones;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.Participante;

import java.util.ArrayList;
import java.util.Arrays;

public class TransferenciaMonetaria implements IAccionJugador {

    /* Atributos */

    private int importe;
    boolean inversion;
    private Participante emisor;
    private ArrayList<Participante> receptores;



    /* Constructor */

    public TransferenciaMonetaria(int importe, boolean inversion, Participante emisor, Participante... receptores ) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado");
            System.exit(1);
        }

        if (receptores == null) {
            System.err.println("Array de receptores no inicializado");
            System.exit(1);
        }

        for (Participante receptor : receptores) {

            if (receptor == null) {
                System.err.println("Receptor no inicializado");
                System.exit(1);
            }
        }

        if (importe < 0) {
            System.err.println("Importe de transferencia negativo");
            System.exit(1);
        }

        this.emisor = emisor;

        this.receptores = new ArrayList<>();
        this.receptores.addAll(Arrays.asList(receptores));

        this.importe = importe;
        this.inversion = inversion;
    }



    /* Getters y setters */

    public int getImporte() {
        return importe;
    }


    public void setImporte(int importe) {

        if( importe < 0 ) {
            System.err.println( "El importe de una transferencia no puede ser negativo" );
            return;
        }

        this.importe = importe;
    }


    public boolean isInversion() {

        return inversion;
    }


    public void setInversion(boolean inversion) {

        this.inversion = inversion;
    }


    public Participante getEmisor() {
        return emisor;
    }


    public void setEmisor(Participante emisor) {

        if( emisor == null ) {
            System.err.println( "Emisor no inicializado" );
            return;
        }

        this.emisor = emisor;
    }

    public ArrayList<Participante> getReceptores() {
        return receptores;
    }


    public void setReceptores(ArrayList<Participante> receptores) {

        if( receptores == null ) {
            System.err.println( "ArrrayList de receptores no inicializado" );
            return;
        }

        for( Participante receptor : receptores ) {

            if( receptor == null ) {
                System.err.println( "Receptor no inicializado" );
                return;
            }
        }

        this.receptores = receptores;
    }



    /* Métodos */

    /**
     * Se deshace la transacción o transacciones efectuadas de un jugador emisor a un conjunto de jugadores receptores,
     * estableciéndose las forturnas a los valores apropiados
     */
    @Override
    public void revertirAccion() {

        // Se devuelve al emisor el importe transferido
        getEmisor().setFortuna( getEmisor().getFortuna() + getImporte() * getReceptores().size() );

        // Y se resta de sus inversiones de ser el caso
        if( isInversion() ) {
            if( getEmisor() instanceof Jugador) {

                final Jugador jugador = (Jugador) getEmisor();
                jugador.setDineroInvertido(jugador.getDineroInvertido() - getImporte() * getReceptores().size());
            }
        }


        // Se resta a los emisores el importe recibido
        for( Participante receptor : getReceptores() )
            receptor.setFortuna( receptor.getFortuna() - getImporte() );
    }
}
