package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoPM2P extends TratoP2P {

    /* Atributos */

    final int cantidadDinero;



    /* Constructor */

    public TratoPM2P(Jugador emisor, Jugador receptor, Propiedad propiedad1, int cantidadDinero, Propiedad propiedad2) {

        super(emisor, receptor, propiedad1, propiedad2);

        if (cantidadDinero < 0) {
            System.err.println("El importe de un trato no puede ser negativo");
            System.exit(1);
        }

        this.cantidadDinero = cantidadDinero;
    }



    /* Getters y setters */

    public int getCantidadDinero() {
        return cantidadDinero;
    }



    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     * @return si se ha podido llevar a cabo el trato
     */
    @Override
    public boolean aceptar() {

        return(aceptar(getEmisor(), getReceptor(), getPropiedad1(), getCantidadDinero(), getPropiedad2()));
    }


    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor         emisor del trato
     * @param receptor       receptor del trato
     * @param propiedad1     propiedad que transfiere el emisor
     * @param cantidadDinero cantidad de dinero que transfiere el emisor
     * @param propiedad2     propiedad que transfiere el receptor
     * @return               si se ha podido llevar a cabo el trato
     */
    public boolean aceptar(Jugador emisor, Jugador receptor, Propiedad propiedad1, int cantidadDinero, Propiedad
            propiedad2) {

        if (emisor.balanceNegativoTrasPago(cantidadDinero)) {
            Output.sugerencia("El emisor no dispone de liquidez suficiente para aceptar el trato");
            return(false);
        }

        if(!super.aceptar(emisor, receptor, propiedad1, propiedad2))
            return(false);

        // Se añade la cantidad de dinero establecida del emisor al receptor
        receptor.setFortuna(receptor.getFortuna() + cantidadDinero);
        emisor.setFortuna(emisor.getFortuna() - cantidadDinero);
        emisor.incrementarDineroInvertido(cantidadDinero);

        Output.respuesta("Se ha transferido la propiedad 1 y la cantidad de dinero especificada por la propiedad 2:",
                "        -> Emisor: " + emisor.getNombre(),
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad 1: " + propiedad1.getNombre(),
                "        -> Cantidad de dinero: " + cantidadDinero + "K €",
                "        -> Propiedad 2: " + propiedad2.getNombre());

        return(true);
    }
}
