package monopoly.jugadores.tratos;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.EstarBancarrotaException;
import monopoly.jugadores.excepciones.NoLiquidezException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

import java.util.ArrayList;

public class Trato {

    /* Atributos */

    private final Jugador emisor;
    private final Jugador receptor;
    private ArrayList<Propiedad> propiedadesDar;
    private ArrayList<Propiedad> propiedadesRecibir;
    private Integer dineroDar;
    private Integer dineroRecibir;
    private ArrayList<Inmunidad> inmunidades;
    private String id;



    /* Constructores */

    public Trato( Jugador emisor, Jugador receptor ) {

        if( emisor == null ) {
            System.err.println( "Emisor no inicializado");
            System.exit( 1 );
        }

        if( receptor == null ) {
            System.err.println( "Receptor no inicializado");
            System.exit( 1 );
        }

        this.propiedadesDar = new ArrayList<>();
        this.propiedadesRecibir = new ArrayList<>();
        this.inmunidades = new ArrayList<>();

        this.dineroDar = 0;
        this.dineroRecibir = 0;

        this.emisor = emisor;
        this.receptor = receptor;
        this.id = "-1";
    }

    public Trato(Jugador emisor, Jugador receptor, ArrayList<Propiedad> propiedadesDar, ArrayList<Propiedad> propiedadesRecibir,
                 Integer dineroDar, Integer dineroRecibir, ArrayList<Inmunidad> inmunidades, String id) {

        if( emisor == null ) {
            System.err.println( "Emisor no inicializado");
            System.exit( 1 );
        }

        if( receptor == null ) {
            System.err.println( "Receptor no inicializado");
            System.exit( 1 );
        }

        if(propiedadesDar == null){
            System.err.println("Propiedades a dar referencia a null");
            System.exit( 1 );
        }

        if(propiedadesRecibir == null){
            System.err.println("Propiedades a recibir referencia a null");
            System.exit( 1 );
        }

        if(dineroDar < 0){
            System.err.println("Dinero a dar no puede ser negativo");
            System.exit( 1 );
        }

        if(dineroRecibir < 0){
            System.err.println("Dinero a recibir no puede ser negativo");
            System.exit( 1 );
        }

        if(inmunidades == null){
            System.err.println("Inmunidades referencia a null");
            System.exit( 1 );
        }

        this.emisor = emisor;
        this.receptor = receptor;
        this.propiedadesDar = propiedadesDar;
        this.propiedadesRecibir = propiedadesRecibir;
        this.dineroDar = dineroDar;
        this.dineroRecibir = dineroRecibir;
        this.inmunidades = inmunidades;
        this.id = id;
    }



    /* Getters y setters */

    public ArrayList<Propiedad> getPropiedadesDar() {
        return propiedadesDar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setPropiedadesDar(ArrayList<Propiedad> propiedadesDar) {
        if(propiedadesDar == null){
            System.err.println("Propiedades a dar referencia a null");
            System.exit( 1 );
        }
        this.propiedadesDar = propiedadesDar;
    }


    public ArrayList<Propiedad> getPropiedadesRecibir() {
        return propiedadesRecibir;
    }


    public void setPropiedadesRecibir(ArrayList<Propiedad> propiedadesRecibir) {

        if(propiedadesRecibir == null){
            System.err.println("Propiedades a recibir referencia a null");
            System.exit( 1 );
        }
        this.propiedadesRecibir = propiedadesRecibir;
    }


    public Integer getDineroDar() {
        return dineroDar;
    }


    public void setDineroDar(Integer dineroDar) {

        if(dineroDar < 0){
            System.err.println("Dinero a dar no puede ser negativo");
            System.exit( 1 );
        }

        this.dineroDar = dineroDar;
    }


    public Integer getDineroRecibir() {
        return dineroRecibir;
    }


    public void setDineroRecibir(Integer dineroRecibir) {

        if(dineroRecibir < 0){
            System.err.println("Dinero a recibir no puede ser negativo");
            System.exit( 1 );
        }
        this.dineroRecibir = dineroRecibir;
    }


    public ArrayList<Inmunidad> getInmunidades() {
        return inmunidades;
    }


    public void setInmunidades(ArrayList<Inmunidad> inmunidades) {

        if(inmunidades == null){
            System.err.println("Inmunidades referencia a null");
            System.exit( 1 );
        }
        this.inmunidades = inmunidades;
    }


    public Jugador getEmisor() {
        return emisor;
    }


    public Jugador getReceptor() {
        return receptor;
    }

    public boolean tratoValido(){
        if(getEmisor().balanceNegativoTrasPago(getDineroDar()))
            return false;

        for(Propiedad propiedad : getPropiedadesDar()){
            if(!propiedad.getPropietario().equals(getEmisor()))
                return false;
        }

        if (getReceptor().balanceNegativoTrasPago(getDineroRecibir()))
            return false;

        for(Propiedad propiedad : getPropiedadesRecibir()){
            if(!propiedad.getPropietario().equals(getReceptor()))
                return false;
        }

        for(Inmunidad inmunidad : getInmunidades()) {
            if (!inmunidad.getPropiedad().getPropietario().equals(receptor))
                return false;
        }

        return true;
    }


    /* Métodos */

    /**
     * Se lleva a cabo el trato propuesto
     * @return si se ha podido llevar a cabo el trato
     */
    public boolean aceptar() throws NoLiquidezException, NoSerPropietarioException{

        if(getEmisor().balanceNegativoTrasPago(getDineroDar()))
            throw new NoLiquidezException("El jugador " + getEmisor().getNombre() + " no dispone de liquidez " +
                    "suficiente para aceptar el trato.");

        for(Propiedad propiedad : getPropiedadesDar()){
            if(!propiedad.getPropietario().equals(getEmisor()))
                throw new NoSerPropietarioException("La propiedad " + propiedad.getNombre() + " no pertenece a "
                        + getEmisor().getNombre());

        }

        if (getReceptor().balanceNegativoTrasPago(getDineroRecibir()))
            throw new NoLiquidezException("El jugador " + getReceptor().getNombre() + " no dispone de liquidez " +
                    "suficiente para aceptar el trato");

        for(Propiedad propiedad : getPropiedadesRecibir()){
            if(!propiedad.getPropietario().equals(getReceptor()))
                throw new NoSerPropietarioException("La propiedad " + propiedad.getNombre() + " no pertenece a "
                        + getReceptor().getNombre());
        }

        for(Inmunidad inmunidad : getInmunidades()) {
            if (!inmunidad.getPropiedad().getPropietario().equals(receptor))
                throw new NoSerPropietarioException("La propiedad " + inmunidad.getPropiedad().getNombre() +
                        " no pertenece a " + receptor.getNombre());
        }

        getEmisor().transferirPropiedades(getEmisor(), getReceptor(), getPropiedadesDar());
        getReceptor().transferirPropiedades(getReceptor(), getEmisor(), getPropiedadesRecibir());


        //En ningún momento se da el caso de que vaya a estar en bancarrota, por eso no se trata.
        if(getDineroDar() > 0) {
            try {
                getEmisor().pagar(getReceptor(), getDineroDar(), false);
            } catch (EstarBancarrotaException e) {
                return false;
            }
        }

        if(getDineroRecibir() > 0) {
            try {
                getReceptor().pagar(getEmisor(), getDineroRecibir(), false);
            } catch (EstarBancarrotaException e) {
                return false;
            }
        }
        getEmisor().getInmunidades().addAll(getInmunidades());

        Output.respuesta("El "+getId()+" ha sido aceptado.");
        return (true);

    }


    @Override
    public String toString(){

        String salida = "";

        salida += "(*) El jugador " + getEmisor().getNombre() + " propone el siguiente trato a " +
                getReceptor().getNombre() + ".\n\n";

        salida += "(!) Id del trato: " + getId()+"\n";

        salida += "    -> Oferta de " + getEmisor().getNombre() + ":\n";

        for(Propiedad propiedad : getPropiedadesDar()){
            salida += "         (-) " + propiedad.getNombre() + ".\n";
        }

        if(getDineroDar() > 0)
            salida += "         (+) " + getDineroDar() + "K €\n";

        salida += "\n    (!) A intercambiar por lo siguiente de parte de " + getReceptor().getNombre() +".\n\n";

        for(Propiedad propiedad : getPropiedadesRecibir()){
            salida += "         (-) " + propiedad.getNombre() + ".\n";
        }

        if(getDineroRecibir() > 0)
            salida += "         (+) " + getDineroRecibir() + "K €\n";

        if(!getInmunidades().isEmpty())
            salida += "         (>) Inmunidades (propiedad, turnos):\n";

        for(Inmunidad inmunidad : getInmunidades()){

            salida += "              - " + inmunidad + ".\n";

        }

        return salida;

    }
}
