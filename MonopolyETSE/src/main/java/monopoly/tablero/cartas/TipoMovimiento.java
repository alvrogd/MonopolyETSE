package monopoly.tablero.cartas;

public enum TipoMovimiento {

    moverAeropuerto( true, "Imperial Destroyer", 0, true, 1 ),
    moverCadiz( true, "Hogwarts", 0, true, 1 ),
    moverCaceres( true, "Endor", 0, true, 1 ),
    moverCarcel( true, "Azkaban", 0, false, 1 ),
    moverLeganes( true, "Braavos", 0, true, 1 ),
    moverTrafico( false, "", -3, true, 1 ),
    moverTransporte( true, "transporte", 0, true, 2 ),
    moverSalida( true, "malloc(game)", 0, true, 1 ),
    moverValencia( true, "Platform 9 3/4", 0, false, 1 ),
    moverPamplona( true, "Moria", 0, true, 1 );



    /* Atributos */

    // Si la casilla es un destino fijo
    private final boolean moverseDirectamente;
    // Nombre de la casilla destino
    private final String nombreCasillaDestino;

    // Si no es un destino fijo, debe saberse cuántas casillas desplazarse
    private final int casillasDesplazarse;

    // Si se debe cobrar un importe si se pasa por la casilla de salida
    private final boolean cobrarCasillaSalida;

    // Multiplicador de pago de alquiler
    private final int multiplicadorPago;



    /* Constructor */
    private TipoMovimiento( boolean moverseDirectamente, String nombreCasillaDestino, int casillasDesplazarse, boolean
            cobrarCasillaSalida, int multiplicadorPago ) {

        this.moverseDirectamente = moverseDirectamente;
        this.nombreCasillaDestino = nombreCasillaDestino;
        this.casillasDesplazarse = casillasDesplazarse;
        this.cobrarCasillaSalida = cobrarCasillaSalida;
        this.multiplicadorPago = multiplicadorPago;

    }



    /* Métodos */

    public boolean isMoverseDirectamente() {
        return moverseDirectamente;
    }

    public int getCasillasDesplazarse() {
        return casillasDesplazarse;
    }

    public String getNombreCasillaDestino() {
        return nombreCasillaDestino;
    }

    public boolean isCobrarCasillaSalida() {
        return cobrarCasillaSalida;
    }

    public int getMultiplicadorPago() {
        return multiplicadorPago;
    }
}
