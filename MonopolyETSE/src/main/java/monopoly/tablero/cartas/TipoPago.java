package monopoly.tablero.cartas;

public enum TipoPago {

    pagarMatriculaColegio( false, 1500, "banca", "" ),
    pagarBienesInmuebles( true, 0, "banca", "" ),
    pagarPresidente( false, 150, "jugadores", "" ),
    pagarMovil( false, 150, "banca", "" ),
    pagarBalneario( false, 500, "banca", "" ),
    pagarViajeLeon( false, 1000, "banca", "" ),
    pagarAlquilerCannes( false, 200, "jugadores", "" );


    /* Atributos */

    // Si el importe a pagar es directo o debe calcularse
    private final boolean importeCalculado;

    // Importe en caso de que sea directo
    private final int importe;

    // Jugador(es) receptor(es)
    private final String nombreReceptor;

    // Descripción de la carta
    private final String descripcion;


    /* Constructor */

    TipoPago(boolean importeCalculado, int importe, String nombreReceptor, String descripcion ) {

        this.importeCalculado = importeCalculado;
        this.importe = importe;
        this.nombreReceptor = nombreReceptor;
        this.descripcion = descripcion;

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

    public String getDescripcion() {
        return descripcion;
    }

}
