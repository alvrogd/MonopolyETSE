package monopoly.tablero.cartas;

import java.util.ArrayList;
import java.util.Arrays;

public enum TipoCobro {

    cobrarBilleteAvion( 500, "Vendes el Halcón Milenario en una subasta ilegal. Cobra 500K€." ),
    cobrarLoteria( 1000, "¡Has llegado a presidente de la República y saqueas las arcas del estado! Cobra 1000K€." ),
    cobrarAcciones( 1500, "¡Le robas al pueblo y Palpatine te apoya! Cobra 1500K€" ),
    cobrarInternet( 2000, "¡Tu compañía de comunicaciones galácticas recibe beneficios! Cobra 2000K€." ),
    cobrarHacienda( 500, "¡Estafas a hacienda y no se da cuenta (o no quiere)! Recibe 500K€." ),
    cobrarJet( 1000, "Recibe 1000K€ de beneficios por alquilas tu escoba mágica." );


    /* Atributos */

    // Importe a recibir
    private final int importe;

    // Descripción de la carta
    private final String[] descripcion;


    /* Constructor */
    private TipoCobro( int importe, String... descripcion ) {

        this.importe = importe;
        this.descripcion = descripcion;

    }


    /* Métodos */

    public int getImporte() {
        return importe;
    }

    public ArrayList<String> getDescripcion() {

        ArrayList<String> des = new ArrayList<>(Arrays.asList(this.descripcion));
        return des;

    }

}
