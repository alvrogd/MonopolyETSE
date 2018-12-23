package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.NoLiquidezException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoP2P extends Trato {

    /* Atributos */

    final Propiedad propiedad1;
    final Propiedad propiedad2;



    /* Constructor */

    public TratoP2P(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2) {

        super(emisor, receptor);

        if (propiedad1 == null) {
            System.err.println("Propiedad 1 no inicializada");
            System.exit(1);
        }

        if (propiedad2 == null) {
            System.err.println("Propiedad 2 no inicializada");
            System.exit(1);
        }

        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
    }



    /* Getters y setters */

    public Propiedad getPropiedad1() {
        return propiedad1;
    }


    public Propiedad getPropiedad2() {
        return propiedad2;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     *
     * @return si se ha podido llevar a cabo el trato
     */
    @Override
    public boolean aceptar() throws NoSerPropietarioException, NoLiquidezException {

        return (aceptar(getEmisor(), getReceptor(), getPropiedad1(), getPropiedad2()));
    }


    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor     emisor del trato
     * @param receptor   receptor del trato
     * @param propiedad1 propiedad que transfiere el emisor
     * @param propiedad2 propiedad que transfiere el receptor
     * @return si se ha podido llevar a cabo el trato
     */
    public boolean aceptar(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2) throws
            NoSerPropietarioException {

        if (!propiedad1.getPropietario().equals(emisor))
            throw new NoSerPropietarioException("La propiedad 1 no pertenece al emisor");

        if (!propiedad2.getPropietario().equals(receptor))
            throw new NoSerPropietarioException("La propiedad 2 no le pertenece");

        // Se cambia el propietario de la primera propiedad
        propiedad1.setPropietario(receptor);
        receptor.getPropiedades().add(propiedad1);
        emisor.getPropiedades().remove(propiedad1);

        // Se cambia el propietario de la segunda propiedad
        propiedad2.setPropietario(emisor);
        emisor.getPropiedades().add(propiedad2);
        receptor.getPropiedades().remove(propiedad2);

        Output.respuesta("Se ha transferido la propiedad 1 por la propiedad 2:",
                "        -> Emisor: " + emisor.getNombre(),
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad 1: " + propiedad1.getNombre(),
                "        -> Propiedad 2: " + propiedad2.getNombre());

        return (true);
    }
}
