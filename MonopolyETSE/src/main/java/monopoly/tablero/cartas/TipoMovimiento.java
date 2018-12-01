package monopoly.tablero.cartas;

public enum TipoMovimiento {

    moverAeropuerto( true, "Gwaihir", 0, true, 1, "Gandalf te llama para una aventura, monta en su Gwaihir. Si pasas por la casilla de Salida, cobra 2M€"),
    moverCadiz( true, "Hogwarts", 0, true, 1, "¡Has recibido tu carta para ir a Hogwarts! Corre a estudiar magia." ),
    moverCaceres( true, "Endor", 0, true, 1, "¡El imperio está atacando Endor! Ve a ayudar a los rebeldes. Si pasas por la casilla de Salida, cobra 2M€" ),
    moverCarcel( true, "Azkaban", 0, false, 1, "El Ministerio de Magia te investiga por colaboración con los mortífagos. Ve a Azkaban. Ve directamente sin pasar por la casilla de Salida\n" +
            "y sin cobrar los 2M€. " ),
    moverLeganes( true, "Braavos", 0, true, 1, "Vas a pedir un préstamo al banco de Braavos. Si pasas por la castilla de Salida cobra los 2M€" ),
    moverTrafico( false, "", -3, false, 1, "Overflow, retrocede tres casillas."),
    moverTransporte( true, "transporte", 0, true, 2, "goto(transportes.next()). Si no tiene dueño, puedes comprársela a la banca. Si \n" +
            "tiene dueño, paga al dueño el doble de la operación indicada. " ),
    moverSalida( true, "malloc(game)", 0, true, 1, "Necesitas reservar memoria, ve a la casilla de Salida. Cobra 2M€"),
    moverValencia( true, "Platform 9 3/4", 0, false, 1, "" ),
    moverPamplona( true, "Moria", 0, true, 1, "" );



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

    // Descripción de la carta
    private final String descripcion;



    /* Constructor */
    private TipoMovimiento( boolean moverseDirectamente, String nombreCasillaDestino, int casillasDesplazarse, boolean
            cobrarCasillaSalida, int multiplicadorPago, String descripcion ) {

        this.moverseDirectamente = moverseDirectamente;
        this.nombreCasillaDestino = nombreCasillaDestino;
        this.casillasDesplazarse = casillasDesplazarse;
        this.cobrarCasillaSalida = cobrarCasillaSalida;
        this.multiplicadorPago = multiplicadorPago;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

}
