package monopoly.tablero.jerarquiaCasillas;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Participante;
import monopoly.jugadores.excepciones.EdificiosSolarException;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solar extends Propiedad{

    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosContenidos;

    public Solar(String nombre, Grupo grupo, boolean comprable, int posicion, Participante propietario, Tablero tablero){

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
     */
    public int edificar(TipoEdificio tipoEdificio, boolean splash) throws EdificiosSolarException{

        Edificio edificacion = null;
        int precio;
        int numHoteles, numCasas, numPiscinas, numPistas, numCasillasGrupo;
        boolean maximo;

        if(tipoEdificio == null){
            System.err.println("El tipo de edificio referencia a null");
            return 0;
        }

        numCasillasGrupo = getGrupo().getPropiedades().size();

        numHoteles = getEdificiosContenidos().get(TipoEdificio.hotel).size();
        numCasas = getEdificiosContenidos().get(TipoEdificio.casa).size();
        numPiscinas = getEdificiosContenidos().get(TipoEdificio.piscina).size();
        numPistas = getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size();

        if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo && numPiscinas == numCasillasGrupo &&
                numPistas == numCasillasGrupo){

            if(splash)
                throw new EdificiosSolarException("No se pueden realizar más edificaciones en esta casilla.");
            return 0;

        }

        switch(tipoEdificio){

            case casa:
                if(numCasas == 4){
                    if(splash)
                        throw new EdificiosSolarException("No se pueden construir más casas en esta casilla.");
                    return 0;
                }
                if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo){
                    if(splash)
                        throw new EdificiosSolarException("No se pueden construir más casas en esta casilla.");
                    return 0;
                }
                edificacion = new Casa(this, getGrupo().getTipo());
                break;

            case hotel:

                if(numCasas != 4){
                    if(splash)
                        throw new EdificiosSolarException("Se necesitan cuatro casas para poder construir un hotel.");
                    return 0;
                }
                if(numHoteles == numCasillasGrupo){
                    if(splash)
                        throw new EdificiosSolarException("No se pueden edificar más hoteles en esta casilla.");
                    return 0;
                }

                destruirEdificio(TipoEdificio.casa, 4);
                edificacion = new Hotel(this, getGrupo().getTipo());

                break;

            case piscina:
                if(numHoteles < 1 || numCasas < 2){
                    if(splash)
                        throw new EdificiosSolarException("Para construir una piscina se necesita al menos un hotel y dos casas.");
                    return 0;
                }

                if(numPiscinas == numCasillasGrupo){
                    if(splash)
                        throw new EdificiosSolarException("No se pueden edificar más piscinas en esta casilla.");
                    return 0;
                }

                edificacion = new Piscina(this, getGrupo().getTipo());

                break;

            case pistaDeporte:
                if(numHoteles < 2){
                    if(splash)
                        throw new EdificiosSolarException("Para construir una pista de deporte se necesitan al menos dos hoteles.");
                    return 0;
                }
                if(numPistas == numCasillasGrupo){
                    if(splash)
                        throw new EdificiosSolarException("No se pueden edificar más pistas de deporte en esta casilla.");
                    return 0;
                }

                edificacion = new PistaDeporte(this, getGrupo().getTipo());

                break;

        }

        getEdificiosContenidos().get(tipoEdificio).add(edificacion);

        setAlquiler(getAlquiler());

        if(splash)
            Output.respuesta("Has creado tu edificio con id " + edificacion.getId());

        actualizarAlquiler();

        return (Edificio.calcularPrecioCompra(tipoEdificio, getGrupo().getTipo()));

    }

    /**
     * Método para añadir a la casilla un edificio del tipo pasado por parámetro.
     * @param tipoEdificio tipo del edificio a edificar
     */
    public int edificar(TipoEdificio tipoEdificio) throws EdificiosSolarException{
        return(edificar(tipoEdificio,true));
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
     * @param tipoEdificio tipo de edificio a vender
     * @param cantidad número de edificios para vender
     * @param splash indicación si se imprimirán mensajes en pantalla
     */
    public int venderEdificio(TipoEdificio tipoEdificio, int cantidad, boolean splash){

        if(cantidad < 1){
            System.err.println("No se puede vender un número negativo de edificios.");
            return 0;
        }
        if(tipoEdificio == null){
            System.err.println("tipoEdificio referencia a null");
            return 0;
        }



        int eliminados = destruirEdificio(tipoEdificio, cantidad);

        if(eliminados == 0){
            if(splash)
                Output.respuesta("No hay edificios de tipo "+tipoEdificio.getNombre()+" en la casilla "+getNombre());
            return 0;
        }

        if(eliminados != cantidad){
            if(splash)
                Output.respuesta("Solo se han podido vender "+eliminados+" edificios de tipo " + tipoEdificio.getNombre() + ".");
        }

        return ((eliminados * Edificio.calcularPrecioCompra(tipoEdificio, getGrupo().getTipo()))/2);

    }

    /**
     * Método para que el propietario de la casilla venda tantos edificios como se indica
     * @param tipoEdificio tipo de edificio a vender
     * @param cantidad número de edificios para vender
     */
    public int venderEdificio(TipoEdificio tipoEdificio, int cantidad){

        return venderEdificio(tipoEdificio, cantidad, true);

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
    public HashSet<TipoFuncion> funcionesARealizar(){

        HashSet<TipoFuncion> funciones = super.funcionesARealizar();

        if(tieneEdificios()){
            funciones.add(TipoFuncion.vender);
        }

        Integer numCasillasGrupo = getGrupo().getPropiedades().size();

        Integer numHoteles = getEdificiosContenidos().get(TipoEdificio.hotel).size();
        Integer numCasas = getEdificiosContenidos().get(TipoEdificio.casa).size();
        Integer numPiscinas = getEdificiosContenidos().get(TipoEdificio.piscina).size();
        Integer numPistas = getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size();

        for(TipoEdificio tipoEdificio : TipoEdificio.values()){

            if(!getEdificiosContenidos().get(tipoEdificio).isEmpty()){

                funciones.add(TipoFuncion.toFuncion(tipoEdificio));

            }

            if(!(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo && numPiscinas == numCasillasGrupo &&
                    numPistas == numCasillasGrupo)){
                funciones.add(TipoFuncion.comprar.edificar);
            }

            switch(tipoEdificio){

                case casa:
                    if(numCasas == 4){
                        break;
                    }
                    if(numHoteles == numCasillasGrupo && numCasas == numCasillasGrupo){
                        break;
                    }
                    funciones.add(TipoFuncion.edificarCasa);
                    break;

                case hotel:

                    if(numCasas != 4){

                        break;
                    }
                    if(numHoteles == numCasillasGrupo){

                        break;
                    }

                    funciones.add(TipoFuncion.edificarHotel);

                    break;

                case piscina:
                    if(numHoteles < 1 || numCasas < 2){

                        break;
                    }

                    if(numPiscinas == numCasillasGrupo){

                        break;
                    }

                    funciones.add(TipoFuncion.edificarPiscina);

                    break;

                case pistaDeporte:
                    if(numHoteles < 2){

                        break;
                    }
                    if(numPistas == numCasillasGrupo){

                        break;
                    }

                    funciones.add(TipoFuncion.edificarPista);

                    break;

            }

        }

        return funciones;

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
                alquilerNuevo = 2 * (int)(getPrecioActual() * Constantes.COEF_ALQUILER);
            }

            //En caso de que el alquilerNuevo no sea cero, bien por los edificios o por haber obtenido los solares
            //se actualiza el alquiler.
            if(alquilerNuevo != 0)
                setAlquiler(alquilerNuevo);
    }

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "\n";
        salida += "        -> Edificios:\n";

        Set<TipoEdificio> keyEdificios = getEdificiosContenidos().keySet();

        for (TipoEdificio auxEdificio : keyEdificios) {

            StringBuilder linea = new StringBuilder();
            ArrayList<Edificio> edificaciones = getEdificiosContenidos().get(auxEdificio);
            int size = edificaciones.size();

            linea.append("           (*) " + auxEdificio.getNombre() + " {");

            for (int i = 0; i < size; i++) {

                linea.append(edificaciones.get(i).getId());

                if (i != size - 1)
                    linea.append(", ");

            }

            if (size == 0) {
                linea.append("No hay edificaciones tipo " + auxEdificio.getNombre());
            }
            linea.append("}");

            salida += linea.toString() + "\n";

        }

        salida += "\n";

        int aux = Edificio.calcularPrecioCompra(TipoEdificio.casa, getGrupo().getTipo());
        salida += "        -> Valor casa:                        " + aux + "K €" + "\n";

        aux = Edificio.calcularPrecioCompra(TipoEdificio.hotel, getGrupo().getTipo());
        salida += "        -> Valor hotel:                       " + aux + "K €" + "\n";

        aux = Edificio.calcularPrecioCompra(TipoEdificio.piscina, getGrupo().getTipo());
        salida += "        -> Valor piscina:                     " + aux + "K €" + "\n";

        aux = Edificio.calcularPrecioCompra(TipoEdificio.pistaDeporte, getGrupo().getTipo());
        salida += "        -> Valor pista de deporte:            " + aux + "K €" + "\n\n";

        int alquiler = (int) (getPrecioInicial() * Constantes.COEF_ALQUILER);

        salida += "        -> Alquiler con una casa:             " + Constantes.ALQ_UNACASA*alquiler + "K €\n";
        salida += "        -> Alquiler con dos casas:            " + Constantes.ALQ_DOSCASA*alquiler + "K €\n";
        salida += "        -> Alquiler con tres casas:           " + Constantes.ALQ_TRESCASA*alquiler + "K €\n";
        salida += "        -> Alquiler con cuatro casas:         " + Constantes.ALQ_CUATROCASA*alquiler + "K €\n";
        salida += "        -> Alquiler con un hotel:             " + Constantes.ALQ_HOTEL*alquiler + "K €\n";
        salida += "        -> Alquiler con una piscina:          " + Constantes.ALQ_PISCINA*alquiler + "K €\n";
        salida += "        -> Alquiler con una pista de deporte: " + Constantes.ALQ_PISTADEPORTE*alquiler + "K €\n";

        return salida;

    }
}
