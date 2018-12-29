package monopoly.tablero.jerarquiaCasillas;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.Participante;
import monopoly.jugadores.excepciones.NoComprarABancaException;
import monopoly.jugadores.excepciones.NoEncontrarseEnPropiedadException;
import monopoly.jugadores.excepciones.NoLiquidezException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.Tablero;

public abstract class Propiedad extends Casilla{

    private final Grupo grupo;

    private Participante propietario;
    private boolean hipotecada;

    //Precio inicial de la casilla
    private int precioInicial;

    //Importe por el cual se ha comprado la propiedad
    private int importeCompra;

    //Alquiler actual
    private int alquiler;

    //Atributo para saber si la propiedad se puede comprar.
    private boolean comprable;

    //Atributo para saber si se ha modificado el alquiler de la casilla desde el último get.
    private boolean seHaModificadoAlquiler;

    private int rentabilidad;



    public Propiedad(String nombre, Grupo grupo, boolean comprable, int posicion, Participante propietario, Tablero tablero){

        super(nombre, posicion, tablero);

        if (grupo == null) {
            System.err.println("Error: grupo no inicializado.");
            System.exit(1);
        }

        if (propietario == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.grupo = grupo;

        this.propietario = propietario;

        this.comprable = comprable;
        this.hipotecada = false;

        this.precioInicial = (int) (grupo.getPrecio() / (double) grupo.getPropiedades().size());
        this.importeCompra = 0;
        this.alquiler = (int) (grupo.getPrecio() / (double) grupo.getPropiedades().size());

        this.rentabilidad = 0;

        this.seHaModificadoAlquiler = false;

    }

    public void setPrecioInicial(int precioInicial) {
        if(precioInicial < 0){
            System.err.println("Precio inicial no puede ser negativo.");
            System.exit(1);
        }
        this.precioInicial = precioInicial;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Participante getPropietario() {
        return propietario;
    }

    public void setPropietario(Participante propietario) {
        if(propietario == null){
            System.err.println("El propietario referencia a null");
            return;
        }
        this.propietario = propietario;
    }

    public boolean isComprable() {
        return comprable;
    }

    public void setComprable(boolean comprable) {
        this.comprable = comprable;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public int getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(int rentabilidad) {
        if(rentabilidad < 0){
            System.err.println("Rentabilidad no puede ser negativa.");
            return;
        }
        this.rentabilidad = rentabilidad;
    }

    public void incrementarRentabilidad(int rentabilidad){
        if(rentabilidad < 0){
            System.err.println("Rentabilidad no puede ser negativa.");
            return;
        }
        setRentabilidad(getRentabilidad()+rentabilidad);
    }

    public int getAlquiler(){

        if(isSeHaModificadoAlquiler()){
            actualizarAlquiler();
        }

        setSeHaModificadoAlquiler(false);

        return alquiler;

    }

    public void setAlquiler(int alquiler){

        if(alquiler < 0){
            System.err.println("Error: el alquiler no puede ser menor a 0.");
            System.exit(1);
        }

        setSeHaModificadoAlquiler(true);

        this.alquiler = alquiler;

    }

    public int getPrecioActual(){
        return((int) (grupo.getPrecio() / (double) grupo.getPropiedades().size()));
    }


    public int getImporteCompra() {
        return importeCompra;
    }

    public void setImporteCompra(int importeCompra) {
        if(importeCompra < 0){
            System.err.println("Importe de compra no puede ser negativo.");
            return;
        }
        this.importeCompra = importeCompra;
    }

    public int getPrecioInicial() {
        return precioInicial;
    }

    public boolean isSeHaModificadoAlquiler() {
        return seHaModificadoAlquiler;
    }

    public void setSeHaModificadoAlquiler(boolean seHaModificadoAlquiler) {
        this.seHaModificadoAlquiler = seHaModificadoAlquiler;
    }

    public void transferirCasilla(Jugador receptor) {

        if (receptor == null) {
            System.err.println("Receptor no inicializado.");
            System.exit(1);
        }

        setPropietario(receptor);
        receptor.getPropiedades().add(this);
        getPropietario().getPropiedades().remove(this);

        Output.respuesta("Se ha transferido la casilla:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Casilla: " + getNombre());

    }

    /**
     * Función para recalcular el alquiler de la casilla.
     */
    public void actualizarAlquiler(){
        return;
    }

    public boolean perteneceAJugador(Jugador jugador){

        return(jugador.equals(getPropietario()));
    }

    public int alquiler() {

        return( getAlquiler() );
    }

    public int valor() {

        return( getPrecioActual() );
    }

    public int comprar( Participante comprador ) throws NoSerPropietarioException, NoComprarABancaException,
            NoEncontrarseEnPropiedadException, NoLiquidezException {

        if( comprador == null ) {

            System.err.println("Comprador no inicializado");
            System.exit(1);
        }

        return(comprador.comprar(getPropietario(), this));
    }

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "\n";
        salida += "        -> Tipo       : " + getGrupo().getTipo().getTipoCasilla() + "\n";
        salida += "        -> Propietario: " + getPropietario().getNombre() + "\n\n";
        salida += "        -> Valor      : " + getPrecioActual() + "K €\n";
        salida += "        -> Alquiler   : " + getAlquiler() + "K €\n";

        return salida;

    }
}
