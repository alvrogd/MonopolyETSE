package aplicacionGUI.editor.filas;

import aplicacionGUI.editor.TipoCasilla;

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

    public Fila(TipoFila tipoFila) {

        if (tipoFila == null) {
            System.out.println("Tipo de fila no inicializado");
            System.exit(1);
        }

        this.tipoFila = tipoFila;

        this.numeroCarcel = 0;
        this.numeroComunidad = 0;
        this.numeroEspecial = 0;
        this.numeroGrupos = 0;
        this.numeroImpuestos = 0;
        this.numeroIrCarcel = 0;
        this.numeroParking = 0;
        this.numeroSalida = 0;
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
        this.numeroCarcel = numeroCarcel;
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
        this.numeroIrCarcel = numeroIrCarcel;
    }

    public int getNumeroParking() {
        return numeroParking;
    }

    public void setNumeroParking(int numeroParking) {
        this.numeroParking = numeroParking;
    }

    public int getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(int numeroSalida) {
        this.numeroSalida = numeroSalida;
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

    public void actualizarNumeroCarcel(int balance) {
        setNumeroCarcel(getNumeroCarcel() + balance);
    }

    public void actualizarNumeroComunidad(int balance) {
        setNumeroComunidad(getNumeroComunidad() + balance);
    }

    public void actualizarNumeroEspecial(int balance) {
        setNumeroEspecial(getNumeroEspecial() + balance);
    }

    public void actualizarNumeroGrupos(int balance) {
        setNumeroGrupos(getNumeroGrupos() + balance);
    }

    public void actualizarNumeroImpuestos(int balance) {
        setNumeroImpuestos(getNumeroImpuestos() + balance);
    }

    public void actualizarNumeroIrCarcel(int balance) {
        setNumeroIrCarcel(getNumeroIrCarcel() + balance);
    }

    public void actualizarNumeroParking(int balance) {
        setNumeroParking(getNumeroParking() + balance);
    }

    public void actualizarNumeroSalida(int balance) {
        setNumeroSalida(getNumeroSalida() + balance);
    }

    public void actualizarNumeroServicios(int balance) {
        setNumeroServicios(getNumeroServicios() + balance);
    }

    public void actualizarNumeroSolares(int balance) {
        setNumeroSolares(getNumeroSolares() + balance);
    }

    public void actualizarNumeroSuerte(int balance) {
        setNumeroSuerte(getNumeroSuerte() + balance);
    }

    public void actualizarNumeroTransportes(int balance) {
        setNumeroTransportes(getNumeroTransportes() + balance);
    }

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
