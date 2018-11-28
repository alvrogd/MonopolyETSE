package monopoly.tablero.cartas;

public enum TipoCobro {

    cobrarBilleteAvion( 500, "" ),
    cobrarLoteria( 1000, "" ),
    cobrarAcciones( 1500, "" ),
    cobrarInternet( 2000, "" ),
    cobrarHacienda( 500, "" ),
    cobrarJet( 1000, "" );


    /* Atributos */

    // Importe a recibir
    private final int importe;

    // Descripción de la carta
    private final String descripcion;


    /* Constructor */
    private TipoCobro( int importe, String descripcion ) {

        this.importe = importe;
        this.descripcion = descripcion;

    }


    /* Métodos */

    public int getImporte() {
        return importe;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
