package monopoly.tablero.cartas;

public enum TipoCobro {

    cobrarBilleteAvion( 500 ),
    cobrarLoteria( 1000 ),
    cobrarAcciones( 1500 ),
    cobrarInternet( 2000 ),
    cobrarHacienda( 500 ),
    cobrarJet( 1000 );


    /* Atributos */
    private final int importe;


    /* Constructor */
    private TipoCobro( int importe ) {

        this.importe = importe;

    }


    /* Métodos */

    public int getImporte() {
        return importe;
    }
}
