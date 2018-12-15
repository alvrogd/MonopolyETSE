package monopoly.tablero.jerarquiaCasillas;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoEdificio;

import java.util.ArrayList;
import java.util.HashMap;

public class Solar extends Propiedad{

    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;

    public Solar(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario, Tablero tablero){

        super(nombre, grupo, comprable, posicion, propietario, tablero);

        ArrayList<Edificio> auxEdificio;

        setAlquiler((int) (Constantes.COEF_ALQUILER * getAlquiler()));

        edificiosContenidos = new HashMap<>();

        for(TipoEdificio aux: TipoEdificio.values()){
            auxEdificio = new ArrayList<>();
            edificiosContenidos.put(aux, auxEdificio);
        }

    }

    public HashMap<TipoEdificio, ArrayList<Edificio>> getEdificiosContenidos() {
        return edificiosContenidos;
    }

    public void setEdificiosContenidos(HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos) {

        if(edificiosContenidos == null){
            System.err.println("Error: edificiosContenidos referencia a null");
            System.exit(1);
        }

        this.edificiosContenidos = edificiosContenidos;
    }

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

        numCasillasGrupo = getGrupo().getSolares().size();

        numHoteles = getEdificiosContenidos().get(TipoEdificio.hotel).size();
        numCasas = getEdificiosContenidos().get(TipoEdificio.casa).size();
        numPiscinas = getEdificiosContenidos().get(TipoEdificio.piscina).size();
        numPistas = getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size();

        if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo && numPiscinas == numCasillasGrupo &&
                numPistas == numCasillasGrupo){

            Output.respuesta("No se pueden realizar más edificaciones en esta casilla.");
            return;

        }

        if(!(comprador.getAvatar().getPosicion() instanceof Solar)){
            Output.respuesta("No se puede edificar en esta casilla.");
            return;
        }

        Solar solar = (Solar) comprador.getAvatar().getPosicion();
        if(comprador.balanceNegativoTrasPago(Edificio.calcularPrecioCompra(tipoEdificio,
                solar.getGrupo().getTipo()))){

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

        edificacion = new Edificio(this, tipoEdificio, getGrupo().getTipo());

        comprador.incrementarDineroInvertido(edificacion.getPrecioCompra());

        comprador.pagar(getGrupo().getTablero().getBanca(), edificacion.getPrecioCompra());

        getEdificiosContenidos().get(tipoEdificio).add(edificacion);

        setAlquiler(getAlquiler());

        Output.respuesta("Has creado tu edificio con id " + edificacion.getId());

        actualizarAlquiler();

    }

    /**
     * Función que destruye el número de edificios indicados.
     * @param tipoEdificio tipo de edificio que se quiere destruir
     * @param cantidad cantidad de edificios a destruir del tipo indicado en tipoEdificio
     */
    public int destruirEdificio(TipoEdificio tipoEdificio, int cantidad){

        if(tipoEdificio == null){
            System.err.println("tipoEdificio referencia a null.");
            return 0;
        }
        //Se obtiene los edificios tipoEdificio que tiene la casilla y se calcula el número que hay.
        ArrayList<Edificio> edificios = getEdificiosContenidos().get(tipoEdificio);
        int size = edificios.size();

        int i;
        for(i = 0; i < cantidad && i < size; i++) {
            edificios.remove(0);
        }

        actualizarAlquiler();

        //Devuelve el número de casillas que ha eliminado
        return i;
    }

    /**
     * Método para que el propietario de la casilla venda tantos edificios como se indica
     * @param jugador propietario de la casilla
     * @param tipoEdificio tipo de edificio a vender
     * @param cantidad número de edificios para vender
     */
    public void venderEdificio(Jugador jugador, TipoEdificio tipoEdificio, int cantidad){

        if(cantidad < 1){
            System.err.println("No se puede vender un número negativo de edificios.");
            return;
        }
        if(jugador == null){
            System.err.println("jugador referencia a null");
            return;
        }
        if(tipoEdificio == null){
            System.err.println("tipoEdificio referencia a null");
            return;
        }
        if(!jugador.equals(getPropietario())){
            Output.mensaje("No puedes vender edificios de una casilla que no es tuya.");
        }

        int eliminados = destruirEdificio(tipoEdificio, cantidad);

        int pago =(eliminados *
                Edificio.calcularPrecioCompra(tipoEdificio, getGrupo().getTipo()))/2;

        jugador.setFortuna(jugador.getFortuna() + pago);

        if(eliminados == 0){

            Output.respuesta("No hay edificios de tipo "+tipoEdificio.getNombre()+" en la casilla "+getNombre());
            return;

        }

        if(eliminados != cantidad){
            Output.respuesta("Solo se han podido vender "+eliminados+" edificios de tipo " + tipoEdificio.getNombre() + ".");
        }

        Output.respuesta("El jugador " + jugador.getNombre() +" ha vendido "+eliminados+" edificios de tipo " +
                tipoEdificio.getNombre() + " por un valor de "+pago+" K €.");
    }

    /**
     * Función que calcula si la casilla actual tiene edificios
     * @return devuelve verdadero en caso de que haya algún edificio en la casilla, falso en caso contrario
     */
    public boolean tieneEdificios(){

        for(TipoEdificio tipoEdificio : TipoEdificio.values()){

            if(!getEdificiosContenidos().get(tipoEdificio).isEmpty()){

                return true;

            }

        }

        return false;

    }


    @Override
    public void actualizarAlquiler() {

            int alquilerNuevo = 0;

            int importe = (int)(Constantes.COEF_ALQUILER * getImporteCompra());

            if(importe == 0)
                importe = (int)(Constantes.COEF_ALQUILER * getPrecioActual());

            for(TipoEdificio aux : TipoEdificio.values()){

                ArrayList<Edificio> edificios = getEdificiosContenidos().get(aux);
                int size = edificios.size();

                switch(aux){

                    case casa:
                        switch(size){
                            case 1:
                                alquilerNuevo += (importe*Constantes.ALQ_UNACASA);
                                break;
                            case 2:
                                alquilerNuevo += (importe*Constantes.ALQ_DOSCASA);
                                break;
                            case 3:
                                alquilerNuevo += (importe*Constantes.ALQ_TRESCASA);
                                break;
                            case 4:
                                alquilerNuevo += (importe*Constantes.ALQ_CUATROCASA);
                                break;
                        }
                        break;

                    case hotel:
                        alquilerNuevo += size*importe*Constantes.ALQ_HOTEL;
                        break;

                    case piscina:
                        alquilerNuevo += size*importe*Constantes.ALQ_PISCINA;
                        break;

                    case pistaDeporte:
                        alquilerNuevo += size*importe*Constantes.ALQ_PISTADEPORTE;
                        break;

                }

            }

            //Si no hay ningún edificio, el alquilerNuevo será igual a 0.

            //En caso de que no haya edificios y el propietario haya obtenido todos los solares del grupo
            //se multiplica el alquiler actual por 2.
            if((alquilerNuevo == 0) && getPropietario().haObtenidoSolaresGrupo(getGrupo())){
                alquilerNuevo = 2 * super.getAlquiler();
            }

            //En caso de que el alquilerNuevo no sea cero, bien por los edificios o por haber obtenido los solares
            //se actualiza el alquiler.
            if(alquilerNuevo != 0)
                setAlquiler(alquilerNuevo);
    }
}
