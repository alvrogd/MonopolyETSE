package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoM2P extends Trato {

    /* Atributos */

    final int cantidadDinero;
    final Propiedad propiedad1;



    /* Constructor */

    public TratoM2P(Jugador emisor, Jugador receptor, int cantidadDinero, Propiedad propiedad1) {

        super(emisor, receptor);

        if (cantidadDinero < 0) {
            System.err.println("El importe de un trato no puede ser negativo");
            System.exit(1);
        }

        if (propiedad1 == null) {
            System.err.println("Propiedad 1 no inicializada");
            System.exit(1);
        }

        this.cantidadDinero = cantidadDinero;
        this.propiedad1 = propiedad1;
    }



    /* Getters y setters */

    public int getCantidadDinero() {
        return cantidadDinero;
    }

    public Propiedad getPropiedad1() {
        return propiedad1;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     *
     * @return si se ha podido llevar a cabo el trato
     */
    @Override
    public boolean aceptar() {

        return (aceptar(getEmisor(), getReceptor(), getCantidadDinero(), getPropiedad1()));
    }


    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor         emisor del trato
     * @param receptor       receptor del trato
     * @param cantidadDinero cantidad de dinero que transfiere el emisor
     * @param propiedad1     propiedad que transfiere el receptor
     * @return si se ha podido llevar a cabo el trato
     */
    public boolean aceptar(Jugador emisor, Jugador receptor, int cantidadDinero, Propiedad propiedad1) {

        if (emisor.balanceNegativoTrasPago(cantidadDinero)) {
            Output.sugerencia("El emisor no dispone de liquidez suficiente para aceptar el trato");
            return (false);
        }

        if (!propiedad1.getPropietario().equals(receptor)) {
            Output.respuesta("La propiedad 2 no le pertenece");
            return (false);
        }

        // Se añade la cantidad de dinero establecida del emisor al receptor
        receptor.setFortuna(receptor.getFortuna() + cantidadDinero);
        emisor.setFortuna(emisor.getFortuna() - cantidadDinero);
        emisor.incrementarDineroInvertido(cantidadDinero);

        // Se cambia el propietario de la primera propiedad
        propiedad1.setPropietario(emisor);
        emisor.getPropiedades().add(propiedad1);
        receptor.getPropiedades().remove(propiedad1);

        Output.respuesta("Se ha transferido la cantidad de dinero especificada por la propiedad:",
                "        -> Emisor: " + emisor.getNombre(),
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Cantidad de dinero: " + cantidadDinero + "K €",
                "        -> Propiedad 1: " + propiedad1.getNombre());

        return (true);
    }
}

