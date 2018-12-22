package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class TratoP2PM extends TratoP2P {

    /* Atributos */

    final int cantidadDinero;



    /* Constructor */

    public TratoP2PM(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, int cantidadDinero) {

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
     */
    @Override
    public void aceptar() {

        aceptar(getEmisor(), getReceptor(), getPropiedad1(), getPropiedad2(), getCantidadDinero());
    }

    /**
     * Se lleva a cabo el trato propuesto
     *
     * @param emisor         emisor del trato
     * @param receptor       receptor del trato
     * @param propiedad1     propiedad que transfiere el emisor
     * @param propiedad2     propiedad que transfiere el receptor
     * @param cantidadDinero cantidad de dinero que transfiere el receptor
     */
    public void aceptar(Jugador emisor, Jugador receptor, Propiedad propiedad1, Propiedad propiedad2, int
            cantidadDinero) {

        if (receptor.balanceNegativoTrasPago(cantidadDinero)) {
            Output.sugerencia("No dispone de liquidez suficiente para aceptar el trato");
            return;
        }

        super.aceptar(emisor, receptor, propiedad1, propiedad2);

        // Se añade la cantidad de dinero establecida del receptor al emisor
        emisor.setFortuna(emisor.getFortuna() + cantidadDinero);
        receptor.setFortuna(receptor.getFortuna() - cantidadDinero);
        receptor.incrementarDineroInvertido(cantidadDinero);

        Output.respuesta("Se ha transferido la propiedad 1 por la propiedad 2 y la cantidad de dinero especificada:",
                "        -> Emisor: " + emisor.getNombre(),
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad 1: " + propiedad1.getNombre(),
                "        -> Propiedad 2: " + propiedad2.getNombre(),
                "        -> Cantidad de dinero: " + cantidadDinero + "K €");
    }
}
