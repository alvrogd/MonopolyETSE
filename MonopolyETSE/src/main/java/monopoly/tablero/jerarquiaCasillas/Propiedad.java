package monopoly.tablero.jerarquiaCasillas;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Tablero;

public abstract class Propiedad extends Casilla{

    private final Grupo grupo;

    private Jugador propietario;
    private boolean hipotecada;

    //Precio inicial de la casilla
    private final int precioInicial;

    //Importe por el cual se ha comprado la propiedad
    private int importeCompra;

    //Alquiler actual
    private int alquiler;

    //Atributo para saber si la propiedad se puede comprar.
    private boolean comprable;

    //Atributo para saber si se ha modificado el alquiler de la casilla desde el último get.
    private boolean seHaModificadoAlquiler;

    private int rentabilidad;



    public Propiedad(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario, Tablero tablero){

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

    public Grupo getGrupo() {
        return grupo;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
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

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "\n";
        salida += "        -> Tipo       : " + getGrupo().getTipo().getTipoCasilla();
        salida += "        -> Propietario: " + getPropietario().getNombre() + "\n\n";
        salida += "        -> Valor      : " + getPrecioActual() + "K €\n";
        salida += "        -> Alquiler   : " + getAlquiler() + "K €\n";

        return salida;

    }
}
