package monopoly.tablero;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {

    /* Atributos */
    private final String nombre;
    private final Grupo grupo;

    private final int posicionEnTablero;
    private Jugador propietario;
    private boolean hipotecada;

    private HashMap<Character, Avatar> avataresContenidos;

    private final int precioInicial;
    private int importeCompra;
    private int alquiler;
    private boolean comprable;

    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;




    /* Constructores */

    public Casilla(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario) {

        if (grupo == null) {
            System.err.println("Error: grupo no inicializado.");
            System.exit(1);
        }

        if (posicion < 0) {
            System.err.println("Error: posición de la casilla en el tablero menor que 0");
            System.exit(1);
        }

        if (propietario == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.nombre = nombre;
        this.grupo = grupo;
        this.posicionEnTablero = posicion;

        this.comprable = comprable;

        this.propietario = propietario;
        this.hipotecada = false;

        avataresContenidos = new HashMap<>();

        ArrayList<Edificio> auxEdificio;

        edificiosContenidos = new HashMap<>();

        for(TipoEdificio aux: TipoEdificio.values()){
            auxEdificio = new ArrayList<>();
            edificiosContenidos.put(aux, auxEdificio);
        }

        this.precioInicial = (int) (grupo.getPrecio() / (double) grupo.getCasillas().size());
        this.importeCompra = 0;
        this.alquiler = (int) (Constantes.COEF_ALQUILER * (grupo.getPrecio() / (double) grupo.getCasillas().size()));

    }

    /*Getters y setters*/

    public String getNombre() {
        return nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public int getPosicionEnTablero() {
        return posicionEnTablero;
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

    public boolean isHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public HashMap<Character, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public void setAvataresContenidos(HashMap<Character, Avatar> avataresContenidos) {
        if(avataresContenidos == null){
            System.err.println("Avatares referencia a null");
            return;
        }
        this.avataresContenidos = avataresContenidos;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public HashMap<TipoEdificio, ArrayList<Edificio>> getEdificiosContenidos() {
        return edificiosContenidos;
    }

    public void setEdificiosContenidos(HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos) {
        if(edificiosContenidos == null){
            System.err.println("Edificios contenidos referencia a null");
            return;
        }
        this.edificiosContenidos = edificiosContenidos;
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

    /*Métodos*/

    /**
     * Método para añadir a la casilla un edificio del tipo pasado por parámetro.
     * @param tipoEdificio tipo del edificio a edificar
     * @param comprador jugador que compra el edificio
     */
    public void edificar(Jugador comprador, TipoEdificio tipoEdificio){

        Edificio edificacion;
        int precio;
        int numHoteles, numCasas, numPiscinas, numPistas, numCasillasGrupo;
        boolean maximo;

        if(comprador == null){
            System.err.println("El comprador referencia a null");
            return;
        }

        if(tipoEdificio == null){
            System.err.println("El tipo de edificio referencia a null");
            return;
        }

        numCasillasGrupo = getGrupo().getCasillas().size();

        numHoteles = getEdificiosContenidos().get(TipoEdificio.hotel).size();
        numCasas = getEdificiosContenidos().get(TipoEdificio.casa).size();
        numPiscinas = getEdificiosContenidos().get(TipoEdificio.piscina).size();
        numPistas = getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size();

        if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo && numPiscinas == numCasillasGrupo &&
                numPistas == numCasillasGrupo){

            Output.respuesta("No se pueden realizar más edificaciones en esta casilla.");
            return;

        }
        if(comprador.balanceNegativoTrasPago(Edificio.calcularPrecioCompra(tipoEdificio,
                comprador.getAvatar().getPosicion().getGrupo().getTipo()))){

            Output.respuesta("El jugador no dispone de suficiente liquidez como para realizar la " +
                    "compra.");

            return;

        }

        precio = (int) tipoEdificio.getCompra() * getPrecioInicial();

        switch(tipoEdificio){

            case casa:
                if(numCasas == 4){
                    Output.respuesta("No se pueden construir más casas en esta casilla.");
                    return;
                }
                if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo){
                    Output.respuesta("No se pueden construir más casas en esta casilla.");
                    return;
                }

                break;

            case hotel:

                if(numCasas != 4){
                    Output.respuesta("Se necesitan cuatro casas para poder construir un hotel.");
                    return;
                }
                if(numHoteles == numCasillasGrupo){
                    Output.respuesta("No se pueden edificar más hoteles en esta casilla.");
                    return;
                }

                destruirEdificio(TipoEdificio.casa, 4);

                break;

            case piscina:
                if(numHoteles < 1 || numCasas < 2){
                    Output.respuesta("Para construir una piscina se necesita al menos un hotel y dos casas.");
                    return;
                }

                if(numPiscinas == numCasillasGrupo){
                    Output.respuesta("No se pueden edificar más piscinas en esta casilla.");
                    return;
                }


                break;

            case pistaDeporte:
                if(numHoteles < 2){
                    Output.respuesta("Para construir una pista de deporte se necesitan al menos dos hoteles.");
                    return;
                }
                if(numPistas == numCasillasGrupo){
                    Output.respuesta("No se pueden edificar más pistas de deporte en esta casilla.");
                    return;
                }
                break;

        }

        edificacion = new Edificio(getGrupo().getTablero(), tipoEdificio, getGrupo().getTipo());

        comprador.pagar(getGrupo().getTablero().getBanca(), edificacion.getPrecioCompra());

        getEdificiosContenidos().get(tipoEdificio).add(edificacion);

        Output.respuesta("Has creado tu edificio con id " + edificacion.getId());

    }

    public void destruirEdificio(TipoEdificio tipoEdificio, int cantidad){

        if(tipoEdificio == null){
            System.err.println("tipoEdificio referencia a null.");
            return;
        }

        ArrayList<Edificio> edificios = getEdificiosContenidos().get(tipoEdificio);
        int size = edificios.size();
        int numEdificios = getGrupo().getTablero().getNumEdificios().get(tipoEdificio);

        for(int i = 0; i < cantidad && i < size; i++) {
            edificios.remove(0);
            numEdificios--;
        }

        getGrupo().getTablero().getNumEdificios().put(tipoEdificio, numEdificios);

    }

    public void venderEdificio(Jugador jugador, TipoEdificio tipoEdificio, int cantidad){

    }


    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final Casilla otro = (Casilla) obj;

        // Si los identificadores de sus avatares son el mismo
        if (this.getPosicionEnTablero() != otro.getPosicionEnTablero()) return (false);

        /* Si no se ha cumplido ninguna condición anterior, son el mismo objeto */
        return (true);

    } /* Fin del método equals */

}
