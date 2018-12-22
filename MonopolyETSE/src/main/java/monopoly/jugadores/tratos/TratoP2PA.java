package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoP2PA extends TratoP2P {

    /* Atributos */

    final Propiedad propiedad3;
    final int numeroTurnos;



    /* Constructor */

    public TratoP2PA(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, Propiedad propiedad3,
                     int numeroTurnos) {

        super(emisor, receptor, propiedad1, propiedad2);

        if (propiedad3 == null) {
            System.err.println("Propiedad 3 no inicializada");
            System.exit(1);
        }

        if (numeroTurnos < 0) {
            System.err.println("El número de turnos de inmunidad no puede ser negativo");
            System.exit(1);
        }

        this.propiedad3 = propiedad3;
        this.numeroTurnos = numeroTurnos;
    }



    /* Getters y setters */

    public Propiedad getPropiedad3() {
        return propiedad3;
    }


    public int getNumeroTurnos() {
        return numeroTurnos;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     */
    @Override
    public void aceptar() {

        aceptar(getEmisor(), getReceptor(), getPropiedad1(), getPropiedad2(), getPropiedad3(), getNumeroTurnos());
    }

    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor       emisor del trato
     * @param receptor     receptor del trato
     * @param propiedad1   propiedad que transfiere el emisor
     * @param propiedad2   propiedad que transfiere el receptor
     * @param propiedad3   propiedad especificada para la inmunidad ante el pago de alquileres
     * @param numeroTurnos número de turnos en los que el emisor gozará de inmunidad ante pagos de alquiler en la
     *                     propiedad especificada
     */
    public void aceptar(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, Propiedad
            propiedad3, int numeroTurnos) {

        super.aceptar(emisor, receptor, propiedad1, propiedad2);

        // Se crea la inmunidad a alquileres en la casilla especificada
        emisor.getInmunidades().add(new Inmunidad(propiedad3, numeroTurnos));

        Output.respuesta("Se ha transferido la propiedad 1 por la propiedad 2 y la inmunidad especificada:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad 1: " + propiedad1.getNombre(),
                "        -> Propiedad 2: " + propiedad2.getNombre(),
                "        -> Propiedad 3: " + propiedad3.getNombre(),
                "        -> Turnos de inmunidad ante alquileres: " + numeroTurnos + " turno(s)");
    }
}
