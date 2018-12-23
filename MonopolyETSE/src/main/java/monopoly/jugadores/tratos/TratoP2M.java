package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.NoLiquidezException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoP2M extends Trato {

    /* Atributos */

    final Propiedad propiedad1;
    final int cantidadDinero;



    /* Constructor */

    public TratoP2M(Jugador emisor, Jugador receptor, Propiedad propiedad1, int cantidadDinero) {

        super(emisor, receptor);

        if (propiedad1 == null) {
            System.err.println("Propiedad 1 no inicializada");
            System.exit(1);
        }

        if (cantidadDinero < 0) {
            System.err.println("El importe de un trato no puede ser negativo");
            System.exit(1);
        }

        this.propiedad1 = propiedad1;
        this.cantidadDinero = cantidadDinero;
    }



    /* Getters y setters */

    public Propiedad getPropiedad1() {
        return propiedad1;
    }


    public int getCantidadDinero() {
        return cantidadDinero;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     *
     * @return si se ha podido llevar a cabo el trato
     */
    @Override
    public boolean aceptar() throws NoSerPropietarioException, NoLiquidezException {

        return (aceptar(getEmisor(), getReceptor(), getPropiedad1(), getCantidadDinero()));
    }


    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor         emisor del trato
     * @param receptor       receptor del trato
     * @param propiedad1     propiedad que transfiere el emisor
     * @param cantidadDinero cantidad de dinero que transfiere el receptor
     * @return si se ha podido llevar a cabo el trato
     */
    public boolean aceptar(Jugador emisor, Jugador receptor, Propiedad propiedad1, int cantidadDinero) throws
            NoSerPropietarioException, NoLiquidezException {

        if (!propiedad1.getPropietario().equals(emisor))
            throw new NoSerPropietarioException("La propiedad 1 no pertenece al emisor");

        if (receptor.balanceNegativoTrasPago(cantidadDinero))
            throw new NoLiquidezException("No dispone de liquidez suficiente para aceptar el trato");

        // Se cambia el propietario de la primera propiedad
        propiedad1.setPropietario(receptor);
        receptor.getPropiedades().add(propiedad1);
        emisor.getPropiedades().remove(propiedad1);

        // Se añade la cantidad de dinero establecida del receptor al emisor
        emisor.setFortuna(emisor.getFortuna() + cantidadDinero);
        receptor.setFortuna(receptor.getFortuna() - cantidadDinero);
        receptor.incrementarDineroInvertido(cantidadDinero);

        Output.respuesta("Se ha transferido la propiedad 1 por la cantidad de dinero especificada:",
                "        -> Emisor: " + emisor.getNombre(),
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad 1: " + propiedad1.getNombre(),
                "        -> Cantidad de dinero: " + cantidadDinero + "K €");

        return (true);
    }
}

