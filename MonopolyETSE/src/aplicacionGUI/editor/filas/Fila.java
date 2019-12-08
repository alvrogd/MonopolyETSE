package aplicacionGUI.editor.filas;

import monopoly.tablero.jerarquiaCasillas.TipoCasilla;

public class Fila {

    /* Atributos */

    // Posición de la fila en el tablero
    private final TipoFila tipoFila;

    // Número de casillas contenidas
    private static int numeroCarcel;
    private int numeroComunidad;
    private int numeroEspecial;
    private int numeroGrupos;
    private int numeroImpuestos;
    private static int numeroIrCarcel;
    private static int numeroParking;
    private static int numeroSalida;
    private int numeroServicios;
    private int numeroSolares;
    private int numeroSuerte;
    private int numeroTransportes;



    /* Constructor */

    /**
     * Se crea una contabilización de las casillas contenidas en una fila
     *
     * @param tipoFila tipo de fila de la cual obtener los máximos de casillas de distintos tipos
     */
    public Fila(TipoFila tipoFila) {

        if (tipoFila == null) {
            System.out.println("Tipo de fila no inicializado");
            System.exit(1);
        }

        // Se registra el tipo de fila
        this.tipoFila = tipoFila;

        // Inicialmente no se contiene ninguna casilla
        Fila.numeroCarcel = 0;
        this.numeroComunidad = 0;
        this.numeroEspecial = 0;
        this.numeroGrupos = 0;
        this.numeroImpuestos = 0;
        Fila.numeroIrCarcel = 0;
        Fila.numeroParking = 0;
        Fila.numeroSalida = 0;
        this.numeroServicios = 0;
        this.numeroSolares = 0;
        this.numeroSuerte = 0;
        this.numeroTransportes = 0;
    }



    /* Getters y setters */

    public int getNumeroCarcel() {
        return numeroCarcel;
    }

    public void setNumeroCarcel(int numeroCarcel) {
        Fila.numeroCarcel = numeroCarcel;
    }

    public TipoFila getTipoFila() {
        return tipoFila;
    }

    public int getNumeroComunidad() {
        return numeroComunidad;
    }

    public void setNumeroComunidad(int numeroComunidad) {
        this.numeroComunidad = numeroComunidad;
    }

    public int getNumeroEspecial() {
        return numeroEspecial;
    }

    public void setNumeroEspecial(int numeroEspecial) {
        this.numeroEspecial = numeroEspecial;
    }

    public int getNumeroGrupos() {
        return numeroGrupos;
    }

    public void setNumeroGrupos(int numeroGrupos) {
        this.numeroGrupos = numeroGrupos;
    }

    public int getNumeroImpuestos() {
        return numeroImpuestos;
    }

    public void setNumeroImpuestos(int numeroImpuestos) {
        this.numeroImpuestos = numeroImpuestos;
    }

    public int getNumeroIrCarcel() {
        return numeroIrCarcel;
    }

    public void setNumeroIrCarcel(int numeroIrCarcel) {
        Fila.numeroIrCarcel = numeroIrCarcel;
    }

    public int getNumeroParking() {
        return numeroParking;
    }

    public void setNumeroParking(int numeroParking) {
        Fila.numeroParking = numeroParking;
    }

    public int getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(int numeroSalida) {
        Fila.numeroSalida = numeroSalida;
    }

    public int getNumeroServicios() {
        return numeroServicios;
    }

    public void setNumeroServicios(int numeroServicios) {
        this.numeroServicios = numeroServicios;
    }

    public int getNumeroSolares() {
        return numeroSolares;
    }

    public void setNumeroSolares(int numeroSolares) {
        this.numeroSolares = numeroSolares;
    }

    public int getNumeroSuerte() {
        return numeroSuerte;
    }

    public void setNumeroSuerte(int numeroSuerte) {
        this.numeroSuerte = numeroSuerte;
    }

    public int getNumeroTransportes() {
        return numeroTransportes;
    }

    public void setNumeroTransportes(int numeroTransportes) {
        this.numeroTransportes = numeroTransportes;
    }



    /* Métodos */

    /**
     * Se actualiza el número de cárceles
     *
     * @param balance cantidad en la que aumentar/disminuir el número de cárceles
     */
    public void actualizarNumeroCarcel(int balance) {
        setNumeroCarcel(getNumeroCarcel() + balance);
    }

    /**
     * Se actualiza el número de casillas de comunidad
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de comunidad
     */
    public void actualizarNumeroComunidad(int balance) {
        setNumeroComunidad(getNumeroComunidad() + balance);
    }

    /**
     * Se actualiza el número de casillas especiales
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas especiales
     */
    public void actualizarNumeroEspecial(int balance) {
        setNumeroEspecial(getNumeroEspecial() + balance);
    }

    /**
     * Se actualiza el número de grupos
     *
     * @param balance cantidad en la que aumentar/disminuir el número de grupos
     */
    public void actualizarNumeroGrupos(int balance) {
        setNumeroGrupos(getNumeroGrupos() + balance);
    }

    /**
     * Se actualiza el número de casillas de impuesto
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de impuesto
     */
    public void actualizarNumeroImpuestos(int balance) {
        setNumeroImpuestos(getNumeroImpuestos() + balance);
    }

    /**
     * Se actualiza el número de casillas de ir a la cárcel
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de ir a la cárcel
     */
    public void actualizarNumeroIrCarcel(int balance) {
        setNumeroIrCarcel(getNumeroIrCarcel() + balance);
    }

    /**
     * Se actualiza el número de parkings
     *
     * @param balance cantidad en la que aumentar/disminuir el número de parkings
     */
    public void actualizarNumeroParking(int balance) {
        setNumeroParking(getNumeroParking() + balance);
    }

    /**
     * Se actualiza el número de salidas
     *
     * @param balance cantidad en la que aumentar/disminuir el número de salidas
     */
    public void actualizarNumeroSalida(int balance) {
        setNumeroSalida(getNumeroSalida() + balance);
    }

    /**
     * Se actualiza el número de casillas de servicio
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de servicio
     */
    public void actualizarNumeroServicios(int balance) {
        setNumeroServicios(getNumeroServicios() + balance);
    }

    /**
     * Se actualiza el número de solares
     *
     * @param balance cantidad en la que aumentar/disminuir el número de solares
     */
    public void actualizarNumeroSolares(int balance) {
        setNumeroSolares(getNumeroSolares() + balance);
    }

    /**
     * Se actualiza el número de casillas de suerte
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de suerte
     */
    public void actualizarNumeroSuerte(int balance) {
        setNumeroSuerte(getNumeroSuerte() + balance);
    }

    /**
     * Se actualiza el número de casillas de transporte
     *
     * @param balance cantidad en la que aumentar/disminuir el número de casillas de transporte
     */
    public void actualizarNumeroTransportes(int balance) {
        setNumeroTransportes(getNumeroTransportes() + balance);
    }

    /**
     * Se comprueba si es posible crear en la fila una casilla más de un tipo dado
     *
     * @param tipoCasilla tipo de casilla a comprobar
     * @return si cabe una casilla más o no del tipo dado
     */
    public boolean masCasillas(TipoCasilla tipoCasilla) {

        int actuales;
        int maximo;

        switch (tipoCasilla) {

            case carcel:
                actuales = getNumeroCarcel();
                maximo = 1;
                break;

            case comunidad:
                actuales = getNumeroComunidad();
                maximo = getTipoFila().getNumeroComunidad();
                break;

            case impuesto:
                actuales = getNumeroImpuestos();
                maximo = getTipoFila().getNumeroImpuestos();
                break;

            case irCarcel:
                actuales = getNumeroIrCarcel();
                maximo = 1;
                break;

            case parking:
                actuales = getNumeroParking();
                maximo = 1;
                break;

            case salida:
                actuales = getNumeroSalida();
                maximo = 1;
                break;

            case servicio:
                actuales = getNumeroServicios();
                maximo = getTipoFila().getNumeroServicios();
                break;

            case solar:
                actuales = getNumeroSolares();
                maximo = getTipoFila().getNumeroSolares();
                break;

            case suerte:
                actuales = getNumeroSuerte();
                maximo = getTipoFila().getNumeroSuerte();
                break;

            case transporte:
            default:
                actuales = getNumeroTransportes();
                maximo = getTipoFila().getNumeroTransportes();
                break;
        }

        return (actuales < maximo);
    }

    /**
     * Se actualiza el número de casillas de un tipo dado
     *
     * @param tipoCasilla tipo de casilla cuya cantidad actualizar
     * @param balance     cantidad en la que aumentar/disminuir el número de casillas
     */
    public void actualizarNumeroCasillas(TipoCasilla tipoCasilla, int balance) {

        switch (tipoCasilla) {

            case carcel:
                actualizarNumeroCarcel(balance);
                actualizarNumeroEspecial(balance);
                break;

            case comunidad:
                actualizarNumeroComunidad(balance);
                break;

            case impuesto:
                actualizarNumeroImpuestos(balance);
                break;

            case irCarcel:
                actualizarNumeroIrCarcel(balance);
                actualizarNumeroEspecial(balance);
                break;

            case parking:
                actualizarNumeroParking(balance);
                actualizarNumeroEspecial(balance);
                break;

            case salida:
                actualizarNumeroSalida(balance);
                actualizarNumeroEspecial(balance);
                break;

            case servicio:
                actualizarNumeroServicios(balance);
                break;

            case solar:
                actualizarNumeroSolares(balance);
                break;

            case suerte:
                actualizarNumeroSuerte(balance);
                break;

            case transporte:
            default:
                actualizarNumeroTransportes(balance);
                break;
        }
    }
}
