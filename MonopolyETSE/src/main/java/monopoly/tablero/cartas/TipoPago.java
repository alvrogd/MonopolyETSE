package monopoly.tablero.cartas;

public enum TipoPago {

    pagarMatriculaColegio( false, 1500, "banca" ),
    pagarBienesInmuebles( true, 0, "banca" ),
    pagarPresidente( false, 150, "jugadores" ),
    pagarMovil( false, 150, "banca" ),
    pagarBalneario( false, 500, "banca" ),
    pagarViajeLeon( false, 1000, "banca" ),
    pagarAlquilerCannes( false, 200, "jugadores" );


    /* Atributos */
    private final boolean importeCalculado;
    private final int importe;
    private final String nombreReceptor;


    /* Constructor */

    TipoPago(boolean importeCalculado, int importe, String nombreReceptor) {

        this.importeCalculado = importeCalculado;
        this.importe = importe;
        this.nombreReceptor = nombreReceptor;

    }


    /* Métodos */

    public boolean isImporteCalculado() {
        return importeCalculado;
    }

    public int getImporte() {
        return importe;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }
}
