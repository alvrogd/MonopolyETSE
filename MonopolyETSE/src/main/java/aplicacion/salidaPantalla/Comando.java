package aplicacion.salidaPantalla;

import aplicacion.Aplicacion;
import aplicacion.TipoComando;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Coche;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.jugadores.excepciones.*;
import monopoly.jugadores.tratos.Inmunidad;
import monopoly.jugadores.tratos.Trato;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.cartas.Carta;
import monopoly.tablero.jerarquiaCasillas.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.Edificio;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Comando implements IComando {

    private ArrayList<String> argv;
    private Aplicacion app;
    private String linea;

    /**
     * Genera los argumentos del comando además de asignar el tipo de comando que es
     *
     * @param linea comando introducido por el usuario
     * @param app   aplicación del juego
     */
    public Comando(String linea, Aplicacion app) {

        if (app == null) {
            System.err.println("Aplicación referencia a null");
            System.exit(-1);
        }

        this.app = app;

        this.argv = separarEspacios(linea);

        this.linea = linea;

    }

    public String getLinea() {
        return linea;
    }

    /**
     * Separa la linea introducida en un Array de palabras.
     *
     * @param linea
     * @return
     */
    private ArrayList<String> separarEspacios(String linea) {

        ArrayList<String> argumentos = new ArrayList<>();
        char[] arrayChar = linea.toCharArray();
        int tamChar = arrayChar.length;
        int comienzo = 0;
        String aux = "";

        //Se busca la primera palabra del comando
        for (int i = 0; i < tamChar && arrayChar[i] != ' '; i++) {

            aux += arrayChar[i];
            comienzo = i;

        }

        //Se añade al array
        argumentos.add(aux);
        aux = "";

        //Se utiliza el método toComandoCasilla para pasar la palabra a un comando que
        //tenga el nombre de una casilla en el en caso de existir, si no devuelve null
        TipoComando tipoComando = TipoComando.toComandoCasilla(argumentos.get(0));
        int numEspacios;

        //Se calculan los espacios que hay hasta la casilla
        if (tipoComando == null) {
            numEspacios = -1; //-1 porque no tiene el nombre de una casilla.
        } else {

            boolean contieneCasilla = true;

            //En caso de que pueda que no sea un comando que contenga casillas
            if(tipoComando.isPuedeContenerCasilla()){
                boolean espacio = true;
                String segundaPalabra = "";

                for(int i = comienzo + 2; espacio && i < tamChar; i++){

                    if(arrayChar[i] == ' '){
                        espacio = false;
                        if(segundaPalabra.equals("jugador") || segundaPalabra.equals("avatar"))
                            contieneCasilla = false;

                    } else {
                        segundaPalabra += arrayChar[i];
                    }

                }

            }

            if(contieneCasilla)
                numEspacios = tipoComando.getEspaciosCasilla() - 1; //-1 porque ya se ha contado un espacio
            else
                numEspacios = -1;

        }

        //Variable para saber si se han buscado más palabras.
        boolean entradoFor = false;
        //Se siguen buscando las palabras, se empieza en comienzo+1 (no se cuenta el anterior espacio
        for (int i = comienzo + 2; i < tamChar; i++) {
            entradoFor = true;
            if (arrayChar[i] == ' ') {

                if (numEspacios != 0) {
                    numEspacios--;
                    argumentos.add(aux);
                    aux = "";
                } else {
                    aux += arrayChar[i];
                }

            } else {
                aux += arrayChar[i];
            }
        }

        if(entradoFor)
            argumentos.add(aux);

        return argumentos;

    }

    public ArrayList<String> getArgv() {
        return argv;
    }

    public Aplicacion getApp() {
        return app;
    }

    public void ejecutarComando() throws Exception{

        int size = getArgv().size();

        if(size < 1){
            Output.errorComando("Comando incorrecto.");
            return;
        }
        switch(getArgv().get(0)){

            case "crear":

                if(size < 2){
                    Output.errorComando("Comando -crear- incorrecto");
                    return;
                }
                switch(getArgv().get(1)){

                    case "jugador":
                        if(size < 4){
                            Output.errorComando("Argumentos del comando -crear jugador- incorrectos.");
                            return;
                        }
                        crearJugador(getArgv().get(2), getArgv().get(3));
                        break;

                    default:
                        Output.errorComando("Argumentos del comando -crear jugador- incorrectos.");

                }
                break;

            case "iniciar":
                iniciarJuego();
                break;

            case "jugador":
                turno();
                break;

            case "listar":
                if(size < 2){
                    Output.errorComando("Comando -listar- incorrecto");
                    return;
                }

                switch(getArgv().get(1)){

                    case "jugadores":
                        listarJugadores();
                        break;

                    case "avatares":
                        listarAvatares();
                        break;

                    case "enventa":
                        listarEnVenta();
                        break;

                    case "edificios":
                        if(size < 3)
                            listarEdificios();
                        else
                            listarEdificiosGrupo(getArgv().get(2));

                        break;

                    default:
                        Output.errorComando("Comando -listar- incorrecto");
                        return;

                }

                break;

            case "lanzar":
                lanzarDados();
                break;

            case "acabar":
                finalizarTurno();
                break;

            case "salir":
                salirCarcel();
                break;

            case "describir":
                if(size < 2){
                    Output.errorComando("Comando -describir- incorrecto");
                    return;
                }
                switch(getArgv().get(1)){

                    case "jugador":
                        if(size < 3){
                            Output.errorComando("Introduzca el jugador");
                            return;
                        }
                        describirJugador(getArgv().get(2));
                        break;

                    case "avatar":
                        if(size < 3){
                            Output.errorComando("Introduzca el avatar.");
                            return;
                        }
                        describirAvatar(getArgv().get(2));
                        break;

                    default:
                        describirCasilla(getArgv().get(1));
                        break;

                }
                break;

            case "comprar":
                if(size < 2){
                    Output.errorComando("Introduzca la casilla");
                    return;
                }
                comprar(getArgv().get(1));
                break;

            case "ver":
                verTablero();
                break;

            case "edificar":
                if(size < 2){
                    Output.errorComando("Indique el tipo de edificio que quiere construir.");
                    return;
                }
                edificar(getArgv().get(1));
                break;

            case "cambiar":
                cambiarModo();
                break;

            case "hipotecar":
                if(size < 2){
                    Output.errorComando("Introduzca la casilla");
                    return;
                }
                hipotecar(getArgv().get(1));
                break;

            case "deshipotecar":
                if(size < 2){
                    Output.errorComando("Introduzca la casilla");
                    return;
                }
                deshipotecar(getArgv().get(1));
                break;

            case "vender":
                if(size < 4){
                    Output.errorComando("Comando -vender- incorrecto.");
                    return;
                }
                vender(getArgv().get(1), getArgv().get(2), getArgv().get(3));
                break;

            case "avanzar":
                avanzar();
                break;

            case "estadisticas":
                if(size < 2){
                    estadisticasGlobales();
                } else {
                    estadisticasJugadores(getArgv().get(1));
                }
                break;

            case "ayuda":
                ayuda();
                break;

            case "suerte":
                ejecutarSuerte();
                break;

            case "comunidad":
                ejecutarComunidad();
                break;

            case "pasta":
                if(size > 1)
                    darPasta(getArgv().get(1));
                break;

            case "pagar":
                if(size > 1)
                    pagar(getArgv().get(1));
                break;

            case "mover":
                if(size > 1)
                    mover(getArgv().get(1));
                break;

            case "trato":
                ejecutarTrato();
                break;

            case "aceptar":
                if(size < 2){
                    Output.errorComando("Comando <<aceptar>> incorrecto.");
                    return;
                }
                aceptarTrato(getArgv().get(1));
                break;

            case "eliminar":
                if(size < 2){
                    Output.errorComando("Comando <<eliminar>> incorrecto.");
                    return;
                }
                eliminarTrato(getArgv().get(1));
                break;

            case "tratos":
                listarTrato();
                break;

            default:
                Output.errorComando("Comando incorrecto");
                break;

        }

    }

    private void mover(String aumento) throws Exception{

        getApp().getJuego().getTurno().getAvatar().mover(Integer.parseInt(aumento), true);

    }

    private void pagar(String dinero){

        getApp().getJuego().getTurno().setFortuna(getApp().getJuego().getTurno().getFortuna() -
                Integer.parseInt(dinero));

    }

    private void darPasta(String dinero){

        getApp().getJuego().getTurno().setFortuna(getApp().getJuego().getTurno().getFortuna() +
                Integer.parseInt(dinero));

    }

    private void ejecutarSuerte() throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException{

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getApp().getJuego().getCartasSuerte().size();

        for(Carta carta : getApp().getJuego().getCartasSuerte()){

            Aplicacion.consola.imprimir("("+count+") "+carta.toString());
            count++;

        }

        Aplicacion.consola.imprimir("(*) Opción: ");
        opc = entrada.nextInt();

        Carta carta = getApp().getJuego().getCartasComunidad().get(opc%size);

        carta.accion();

    }

    private void ejecutarComunidad() throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException{

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getApp().getJuego().getCartasComunidad().size();

        for(Carta carta : getApp().getJuego().getCartasComunidad()){

            Aplicacion.consola.imprimir("("+count+") "+carta.toString());
            count++;

        }

        Aplicacion.consola.imprimir("(*) Opción: ");
        opc = entrada.nextInt();

        Carta carta = getApp().getJuego().getCartasComunidad().get(opc%size);

        carta.accion();

    }

    public void crearJugador(String nombre, String avatar) {
        int argc = getArgv().size();

        if (getApp().getJuego().isIniciado()) {

            Output.errorComando("El juego ya está iniciado. No se pueden añadir más jugadores.");
            return;

        }

        if (getApp().getJuego().getNombresJugadores().size() == 6) {
            Output.errorComando("Has alcanzado el número máximo de jugadores");
            return;
        }

        if (getApp().getJuego().getNombresJugadores().contains(nombre)) {
            Output.errorComando("Ese jugador ya pertenece al juego.");
            return;
        }

        TipoAvatar tipoAvatar = TipoAvatar.toAvatar(avatar);

        if (tipoAvatar == null) {

            Output.errorComando("Avatar incorrecto en la opción -crear-");

            ArrayList<String> sugerencia = new ArrayList<>();

            sugerencia.add("Los avatares disponibles son los siguientes:");
            sugerencia.add("    -> Coche.");
            sugerencia.add("    -> Esfinge.");
            sugerencia.add("    -> Pelota.");
            sugerencia.add("    -> Sombrero.");

            Output.sugerencia(sugerencia);

            return;

        }

        Jugador jugador = new Jugador(nombre, getApp().getJuego().getTablero(), tipoAvatar,
                getApp().getJuego().getTablero().getCasillas().get(0).get(0));

        getApp().getJuego().addJugador(jugador);
        Output.respuesta("¡Jugador creado!",
                "        -> Nombre: " + jugador.getNombre(),
                "        -> Avatar: " + jugador.getAvatar().getIdentificador());

    }

    public void iniciarJuego() {

        if (getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego ya está iniciado.");
            return;
        }
        if (getApp().getJuego().getNombresJugadores().size() < 2) {
            Output.errorComando("No hay suficientes jugadores para empezar el juego");
            return;
        }

        ArrayList<String> aux = new ArrayList<>();

        aux.add("¡Se ha iniciado el juego!");
        Output.imprimirRecuadro(aux, "Información: ", TipoColor.verdeANSI, 3, 1);
        getApp().getJuego().iniciarJuego();

    }

    public Jugador turno() {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return null;
        }

        ArrayList<String> auxiliar = new ArrayList<>();
        ArrayList<String> informacionEnviar = new ArrayList<>();

        auxiliar = Output.toArrayString(getApp().getJuego().getTurno().toString());

        informacionEnviar.add("Información del jugador que tiene el turno.");

        //Elimino la información de propiedades y propiedades hipotecadas ya que no es necesaria
        for (int i = 0; i < 2; i++) {
            informacionEnviar.add(auxiliar.get(i));
        }

        Output.respuesta(informacionEnviar);

        return (getApp().getJuego().getTurno());

    }

    public void listarJugadores() {

        //Listar jugadores, en respuesta se añade la información que se pasará a output.
        ArrayList<String> respuesta = new ArrayList<>();

        //Se almacenan los nombres de los jugadores
        ArrayList<String> jugadores;

        //Array donde está la información recibida de JugadorToArrayString
        ArrayList<String> infoEnviar;

        //Se comprueba si el juego se ha iniciado o no
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        jugadores = getApp().getJuego().getNombresJugadores();

        //1ª línea del mensaje
        respuesta.add("Los jugadores que participan en el juego son, con su respectivo orden: ");

        //Variable para guardar el tamaño del array recibido por JugadorToArrayString
        int tam;

        for (String jug : jugadores) {
            //Se obtiene la información del jugador con la función JugadortoArrayString
            infoEnviar = Output.toArrayString(getApp().getJuego().getJugador(jug).toString());

            //Nueva línea
            respuesta.add("");

            //Se añade el nombre del jugador

            tam = infoEnviar.size();

            //Se imprime la información de infoEnviar, exceptuando el nombre del jugador, por eso j = 1.
            for (int j = 0; j < tam; j++)
                respuesta.add(infoEnviar.get(j));
        }

        Output.respuesta(respuesta);

    }

    public void listarAvatares() {

        ArrayList<String> resp = new ArrayList<>();
        ArrayList<String> jug;
        ArrayList<String> avatares;
        int tam;

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        jug = getApp().getJuego().getNombresJugadores();


        resp.add("Los avatares que hay en el tablero son los siguientes en su correspondiente orden:");

        for (String nombre : jug) {

            avatares = Output.toArrayString(getApp().getJuego().getJugador(nombre).getAvatar().toString());

            resp.add("");

            tam = avatares.size();
            for (int i = 0; i < tam; i++) {
                resp.add(avatares.get(i));
            }

        }

        Output.respuesta(resp);

    }

    public void listarEdificios() {

        ArrayList<String> respuesta = new ArrayList<>();
        boolean flag = false;

        respuesta.add("Los edificios del tablero son los siguientes.");


        for (ArrayList<Casilla> fila : getApp().getJuego().getTablero().getCasillas()) {

            for (Casilla casilla : fila) {

                if (casilla instanceof Solar) {

                    Solar solar = (Solar) casilla;

                    for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                        for (Edificio edificio : solar.getEdificiosContenidos().get(tipoEdificio)) {

                            flag = true;
                            respuesta.addAll(Output.toArrayString(edificio.toString()));
                            respuesta.add(" ");

                        }
                    }
                }
            }
        }


        if (!flag) {

            respuesta.add("(!) No hay edificios en el tablero.");

        }

        Output.respuesta(respuesta);
    }

    public void listarEdificiosGrupo(String stringGrupo) {

        TipoGrupo tipoGrupo = null;
        for (TipoGrupo aux : TipoGrupo.values()) {
            if (aux.toString().equals(stringGrupo)) {
                tipoGrupo = aux;
                break;
            }
        }

        if (tipoGrupo == null) {
            Output.respuesta("Ese grupo no existe.");
            return;
        }

        Grupo grupo = getApp().getJuego().getTablero().getGrupos().get(tipoGrupo);

        //ArrayList de String donde se almacenará la respuesta.
        ArrayList<String> res = new ArrayList<>();

        //ArrayList auxiliar para almacenar un ArrayList de edificios obtenido a partir de un HashMap
        ArrayList<Edificio> auxEdificios;

        //StringBuilder que se utiliza para ir generando el mensaje para cada edificio
        StringBuilder idEdificios;

        //Número de casillas que tiene el grupo
        int numCasillas = grupo.getPropiedades().size();

        res.add("Los edificios del grupo " + tipoGrupo.toString() + " son los siguientes.");

        boolean flagGrupo = false;

        for (Casilla casilla : grupo.getPropiedades()) {

            if (casilla instanceof Solar) {

                Solar solar = (Solar) casilla;

                if (!solar.isComprable()) {

                    flagGrupo = true;

                    res.add("(*) Propiedad: " + solar.getNombre());

                    for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                        //Variable para contar número de edificios que hay en cada solar
                        int numEdificio = 0;

                        //Variable donde se almacenará el número de edificios del tipo actual que se pueden construir
                        int construcciones;

                        idEdificios = new StringBuilder();

                        idEdificios.append("    -> ").append(tipoEdificio.getPlural()).append(tipoEdificio.getEspacios()).append(": {");

                        //Se obtienen los edificios contenido del tipo actual.
                        auxEdificios = solar.getEdificiosContenidos().get(tipoEdificio);

                        for (Edificio edificio : auxEdificios) {

                            idEdificios.append(edificio.getId());
                            idEdificios.append(",");

                            numEdificio++;

                        } // Fin del for de edificios

                        if (numEdificio == 0) {
                            idEdificios.append("No hay ").append(tipoEdificio.getPlural()).append("}");
                        } else {
                            int length = idEdificios.length();
                            idEdificios.replace(length - 1, length - 1, "}");
                        }

                        if (tipoEdificio.equals(TipoEdificio.casa) &&
                                (solar.getEdificiosContenidos().get(TipoEdificio.hotel).size() != numCasillas)) {
                            construcciones = 4 - numEdificio;

                        } else {
                            construcciones = numCasillas - numEdificio;
                        }
                        
                        if (construcciones == 0) {
                            idEdificios.append(" | No se pueden construir más ").append(tipoEdificio.getPlural());
                        } else {
                            idEdificios.append(" | Se pueden construir ").append(construcciones).append(" ").append(tipoEdificio.getPlural());
                        }
                        res.add(idEdificios.toString());

                    } // Fin del for de tipoEdificio

                    res.add("    -> Alquiler: " + solar.getAlquiler());
                    res.add(" ");
                } // Fin del if para ver si es comprable
            } // Fin del instanceof
        } // Fin del bucle de casillas

        if (!flagGrupo) {

            res.add("(!) No hay edificios en este grupo.");

        }

        Output.respuesta(res);

    }

    public void lanzarDados() throws EstarPenalizadoException, ImposibleMoverseException,
            EstarBancarrotaException, NoSerPropietarioException, NoEstarEncarceladoException,
            ImposibleCambiarModoException{

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        if (getApp().getJuego().isHaLanzadoDados()) {
            Output.errorComando("Ya has lanzado los dados.");
            return;
        }

        if (getApp().getJuego().getTurno().getAvatar() instanceof Coche)
            getApp().getJuego().setHaCompradoPropiedad(false);

        getApp().getJuego().getTurno().lanzarDados(getApp().getJuego().getTablero().getDado());

    }

    public void finalizarTurno() {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }
        if (!getApp().getJuego().isHaLanzadoDados()) {
            Output.errorComando("¡No ha lanzado los dados!");
            return;
        }

        getApp().getJuego().finalizarTurno();

        Output.respuesta("El jugador actual es " + getApp().getJuego().getTurno().getNombre());

    }

    public void salirCarcel() throws NoEstarEncarceladoException, SeHanLanzadoDadosException, NoLiquidezException,
            EstarBancarrotaException, NoSerPropietarioException{

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        getApp().getJuego().getTurno().getAvatar().salirCarcel();

    }

    public void describirCasilla(String nombreCasilla) {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get(nombreCasilla);

        if (casilla == null) {
            Output.errorComando("La casilla indicada no existe.");
            return;
        }

        Output.respuesta(Output.toArrayString(casilla.toString()));
    }

    public void describirJugador(String nombreJugador) {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        Jugador jugador = getApp().getJuego().getJugadores().get(nombreJugador);

        if (jugador == null) {
            Output.errorComando("Ese jugador no existe.");
            return;
        }

        Output.respuesta(Output.toArrayString(jugador.toString()));
    }

    public void describirAvatar(String idAvatar) {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        Avatar avatar1 = getApp().getJuego().getTablero().getAvataresContenidos().get(((idAvatar).charAt(0)));

        if (avatar1 == null) {
            Output.errorComando("Ese avatar no existe.");
            return;
        }
        Output.respuesta(Output.toArrayString(avatar1.toString()));
    }

    public void comprar(String nombrePropiedad) throws NoEncontrarseEnPropiedadException, NoComprarABancaException,
            NoLiquidezException, NoSerPropietarioException{
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        if ((getApp().getJuego().isHaCompradoPropiedad() && getApp().getJuego().getTurno().getAvatar() instanceof Coche) &&
                !getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
            Output.sugerencia("Ya has comprado una casilla en este turno.");
            return;
        }

        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get((nombrePropiedad));

        if (casilla == null) {
            Output.errorComando("Esa casilla no existe.");
            return;
        }

        if(!(casilla instanceof Propiedad)){
            Output.errorComando("La casilla " + nombrePropiedad + " no es una propiedad.");
            return;
        }

        getApp().getJuego().getTurno().comprar(getApp().getJuego().getBanca(), (Propiedad) casilla);
    }

    public void listarEnVenta() {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        ArrayList<String> respuesta1 = new ArrayList<>();

        ArrayList<String> auxiliar1;

        int tamArray;

        for (ArrayList<Casilla> fila : getApp().getJuego().getTablero().getCasillas()) {
            for (Casilla casillaAux : fila) {
                if (casillaAux instanceof Propiedad) {

                    Propiedad solar = (Propiedad) casillaAux;

                    if (solar.isComprable()) {

                        auxiliar1 = Output.toArrayString(casillaAux.toString());
                        tamArray = auxiliar1.size();
                        respuesta1.add("");
                        for (int i = 0; i < tamArray; i++) {
                            respuesta1.add(auxiliar1.get(i));
                        } //Fin del for
                    }//Fin del solar.isComprable
                }//Fin del instanceof
            }//Fin del for
        }//Fin del for

        Output.respuesta(respuesta1);
    }

    public void verTablero() {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }
    }

    public void edificar(String tipoEdificio) throws NoSerPropietarioException, HipotecaPropiedadException {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        TipoEdificio edificio = TipoEdificio.toEdificio(tipoEdificio);

        Jugador turno = getApp().getJuego().getTurno();

        if(turno.getAvatar().getPosicion() instanceof Solar){

            if (edificio != null)
                getApp().getJuego().getTurno().crearEdificio(edificio, (Solar)getApp().getJuego().getTurno().getAvatar().getPosicion());
            else
                Output.errorComando("Opción del comando -edificar- incorrecta, tipo de edificio incorrecto.");

        } else {

            Output.sugerencia("La casilla no es un solar.");

        }
    }

    public void cambiarModo() throws ImposibleCambiarModoException {
        boolean isEstandar;

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        getApp().getJuego().getTurno().getAvatar().switchMovimiento();
    }

    public void hipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            EdificiosSolarException {
        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get(nombrePropiedad);

        if (casilla == null) {
            Output.errorComando("La casilla " + nombrePropiedad + " no existe.");
            return;
        }

        if(!(casilla instanceof Propiedad)){
            Output.errorComando("La casilla " + nombrePropiedad + " no es una propiedad.");
            return;
        }

        getApp().getJuego().getTurno().hipotecar((Propiedad)casilla);
    }

    public void deshipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            NoLiquidezException {
        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get(nombrePropiedad);

        if (casilla == null) {
            Output.errorComando("La casilla " + nombrePropiedad + " no existe.");
            return;
        }

        if(!(casilla instanceof Propiedad)){
            Output.errorComando("La casilla " + nombrePropiedad + " no es una propiedad.");
            return;
        }

        getApp().getJuego().getTurno().deshipotecar((Propiedad)casilla);
    }

    public void vender(String tipoEdificio, String numero, String nombrePropiedad) throws NoSerPropietarioException,
            HipotecaPropiedadException, EdificiosSolarException, InputUsuarioException{
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        TipoEdificio edificio = TipoEdificio.toEdificio(tipoEdificio);

        if (edificio == null) {
            Output.errorComando("Opción del comando -vender- incorrecta, tipo de edificio incorrecto.");
            return;
        }

        Casilla casillaV = getApp().getJuego().getTablero().getCasillasTablero().get(nombrePropiedad);

        if (casillaV == null) {
            Output.errorComando("La casilla " + nombrePropiedad + " no existe.");
            return;
        }

        if(!(casillaV instanceof Solar)){
            Output.errorComando("La casilla " + nombrePropiedad + " no es un solar.");
            return;
        }

        int cantidad;

        if(Aplicacion.consola.isInteger(numero))
            cantidad = Integer.parseInt(numero);
        else{
            Output.errorComando("El número introducido es incorrecto.");
            return;
        }

        getApp().getJuego().getTurno().venderEdificio(edificio, cantidad, (Solar)casillaV);
    }

    public void avanzar() throws ImposibleMoverseException, EstarBancarrotaException, NoSerPropietarioException,
            ImposibleCambiarModoException{
        int casillasPorMoverse = getApp().getJuego().getTurno().getAvatar().getCasillasRestantesPorMoverse();

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }
        if (casillasPorMoverse <= 0) {
            Output.mensaje("No te quedan casillas por moverte, vuelve a tirar los dados.");
            return;
        }

        getApp().getJuego().getTurno().getAvatar().avanzar(casillasPorMoverse);
    }

    public void estadisticasJugadores(String nombreJugador) {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("No se ha iniciado el juego.");
            return;
        }

        Jugador auxJugador;

        auxJugador = getApp().getJuego().getJugadores().get(nombreJugador);

        if (auxJugador == null) {
            Output.errorComando("No existe el jugador " + nombreJugador + ".");
            return;
        }

        Output.respuesta("(*) Estadísticas de " + auxJugador.getNombre(),
                "      -> Dinero invertido           : " + auxJugador.getDineroInvertido(),
                "      -> Pago tasas e impuestos     : " + auxJugador.getPagoTasasEImpuestos(),
                "      -> Pago de alquileres         : " + auxJugador.getPagoDeAlquileres(),
                "      -> Cobro de alquileres        : " + auxJugador.getCobroDeAlquileres(),
                "      -> Pasar por casilla de salida: " + auxJugador.getPasarPorCasillaDeSalida(),
                "      -> Premios inversiones o botes: " + auxJugador.getPremiosInversionesOBote(),
                "      -> Veces en la carcel         : " + auxJugador.getVecesEnLaCarcel());

    }

    public void estadisticasGlobales() {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("No se ha iniciado el juego.");
            return;
        }
        Output.respuesta("(*) Estadísticas globales",
                "      -> Casilla más rentable    : " + getApp().getJuego().casillaMasRentable().getNombre(),
                "      -> Grupo más rentable      : " + getApp().getJuego().grupoMasRentable().getTipo().toString(),
                "      -> Casilla más frecuentada : " + getApp().getJuego().casillaMasFrecuentada().getNombre(),
                "      -> Jugador con más vueltas : " + getApp().getJuego().jugadorMasVueltas().getNombre(),
                "      -> Jugador con más tiradas : " + getApp().getJuego().jugadorMasVecesDados().getNombre(),
                "      -> Jugador en cabeza       : " + getApp().getJuego().jugadorEnCabeza().getNombre());
    }

    public void ayuda() {
        Output.imprimirAyuda();
    }

    /**
     * Pasa el array de Char introducido a un String, sin incluir los caracteres '\0'
     */
    private String arrayCharToString(char[] array){

        String salida = "";

        for(char letra : array){
            if(letra != '\0')
                salida += letra;
        }

        return salida;
    }

    private ArrayList<String> separar(String linea, char separacion){

        String aux = "";
        char[] array = linea.toCharArray();
        boolean modificado = false;
        ArrayList<String> salida = new ArrayList<>();

        for(char letra : array){

            if(letra != separacion) {
                aux += letra;
                modificado = true;
            }
            else{
                if(modificado)
                    salida.add(eliminarEspacioInicialFinal(aux));
                aux = "";
                modificado = false;
            }
        }

        if(modificado)
            salida.add(eliminarEspacioInicialFinal(aux));

        return salida;
    }

    private String eliminarEspacioInicialFinal(String linea){

        char[] array = linea.toCharArray();

        if(array[0] == ' '){

            array[0] = '\0';

        }

        if(array[array.length-1] == ' '){

            array[array.length-1] = '\0';

        }

        if(array[array.length - 1] == ')'){

            array[array.length-1] = '\0';

        }

        return(arrayCharToString(array));

    }

    /**
     * Función para interpretar los argumentos del subcomando cambiar del trato
     * @param linea argumentos del subcomando, sin paréntesis '('
     * @return devuelve el siguiente Array: (Array con propiedades a dar, Integer de dinero a dar, Array con propiedades
     * a recibir, Integer dinero a recibir)
     * En caso de que alguna de estas características no sean introducidas en el comando se introducirá un null en su
     * correspondiente lugar, por lo tanto es necesario realizar una comprobación previa.
     */
    private ArrayList<Object> interpretarCambiar(String linea){

        //Se van a separar los argumentos mediante ',' para diferenciar la parte a dar por el usuario, de la parte que
        //recibe.

        ArrayList<Object> salida = new ArrayList<>();
        Integer dineroDar = 0;
        Integer dineroRecibir = 0;
        ArrayList<Propiedad> propiedadDar = new ArrayList<>();
        ArrayList<Propiedad> propiedadRecibir = new ArrayList<>();

        linea = eliminarEspacioInicialFinal(linea);

        ArrayList<String> partes = separar(linea, ',');

        //Se comprueba que solo haya una única coma, por lo tanto dos Strings

        if(partes.size() != 2){
            Output.errorComando("Error en <<cambiar>> del comando trato.");
            return null;
        }

        String argumentoDar = partes.get(0);
        String argumentoRecibir = partes.get(1);

        //A continuación se separa cada uno de ellos mediante 'y' para determinar las cosas a cambiar.
        ArrayList<String> separacionDar = separar(argumentoDar, '&');

        //Se interpretan los argumentos introducidos para dar al usuario
        for(String arg : separacionDar){

            //Para cada argumentos se eliminan los posibles espacios iniciales y finales que se hayan quedado
            String auxiliar = eliminarEspacioInicialFinal(arg);

            if(Aplicacion.consola.isInteger(auxiliar)){
                dineroDar+=(Integer.parseInt(auxiliar));
            } else if(getApp().getJuego().isPropiedad(auxiliar)){
                propiedadDar.add((Propiedad)getApp().getJuego().getTablero().getCasillasTablero().get(auxiliar));
            } else {
                Output.errorComando(auxiliar+" no es ni un entero ni una propiedad.");
                return null;
            }

        }

        //Se elimina el ')' del subcomando en caso de que exista.
        argumentoRecibir = separar(argumentoRecibir, ')').get(0);
        ArrayList<String> separacionRecibir = separar(argumentoRecibir, '&');

        for(String arg : separacionRecibir){

            //Para cada argumentos se eliminan los posibles espacios iniciales y finales que se hayan quedado
            String auxiliar = eliminarEspacioInicialFinal(arg);

            if(Aplicacion.consola.isInteger(auxiliar)){
                dineroRecibir+=(Integer.parseInt(auxiliar));
            } else if(getApp().getJuego().isPropiedad(auxiliar)){
                propiedadRecibir.add((Propiedad)getApp().getJuego().getTablero().getCasillasTablero().get(auxiliar));
            } else {
                Output.errorComando(auxiliar+" no es ni un entero ni una propiedad.");
                return null;
            }

        }

        salida.add(propiedadDar);
        salida.add(dineroDar);
        salida.add(propiedadRecibir);
        salida.add(dineroRecibir);

        return salida;

    }

    /**
     * Función para interpretar los argumentos del subcomando noalquiler del trato
     * @param linea argumentos del subcomando, sin paréntesis '('
     * @return devuelve el siguiente Array: (Array con propiedades a las que será inmune, Integer turnos de inmunidad)
     */
    private ArrayList<Object> interpretarNoAlquiler(String linea){

        ArrayList<Object> salida = new ArrayList<>();
        ArrayList<Propiedad> propiedadesInmune = new ArrayList<>();
        Integer numTurnos;

        linea = eliminarEspacioInicialFinal(linea);

        ArrayList<String> partes = separar(linea, ',');

        //Solo puede haber dos partes, la de las propiedades y la del número de turnos
        if(partes.size() != 2){

            Output.errorComando("Error en <<noalquiler>> del comando trato.");
            return null;

        }

        ArrayList<String> nombrePropiedades = separar(partes.get(0), '&');

        for(String nombre : nombrePropiedades){

            if(getApp().getJuego().isPropiedad(nombre)){

                propiedadesInmune.add((Propiedad) getApp().getJuego().getTablero().getCasillasTablero().get(nombre));

            } else {

                Output.errorComando(nombre + " no es una propiedad.");
                return null;

            }

        }

        if(Aplicacion.consola.isInteger(partes.get(1))){

            numTurnos = Integer.parseInt(partes.get(1));
            if(numTurnos < 0){
                Output.errorComando(partes.get(1) + " no es un número válido.");
                return null;
            }

        } else {
            Output.errorComando(partes.get(1) + " no es un número válido.");
            return null;
        }

        salida.add(propiedadesInmune);
        salida.add(numTurnos);

        return salida;

    }

    /**
     * Función para interpretar la línea de comando del trato introducido.
     * @param linea línea del comando introducido por el usuario
     * @return devuelve un ArrayList que contendrá lo siguiente: (ArrayList con propiedades a dar, Integer de dinero a dar,
     * ArrayList propiedades a recibir, Integer dinero a recibir, ArrayList que contiene tuplas (propiedad, turno) de inmunidad,
     * Jugador receptor)
     * En caso de que alguna de estas características no sean introducidas en el comando se introducirá un null en su
     * correspondiente lugar, por lo tanto es necesario realizar una comprobación previa.
     */
    private ArrayList<Object> interpretarTrato(String linea){

        ArrayList<Object> salida = new ArrayList<>();
        Integer dineroDar = 0;
        Integer dineroRecibir = 0;
        ArrayList<Propiedad> propiedadDar = new ArrayList<>();
        ArrayList<Propiedad> propiedadRecibir = new ArrayList<>();

        //Cada elemento contiene una tupla (propiedad, turnos)
        ArrayList<Inmunidad> propiedadesInmune = new ArrayList<>();

        ArrayList<String> separacionEspacio = separar(linea, ' ');

        if(separacionEspacio.size() <= 2){
            Output.errorComando("El trato introducido es incorrecto.");
            return null;
        }

        String aux = separacionEspacio.get(0); //se va a comprobar que la primera palabra sea igual a "trato"
        if(!(aux.equals("trato") || aux.equals(("Trato")))){
            Output.errorComando("El trato introducido es incorrecto.");
            return null;
        }

        //A continuación se separa la línea entera en mediante los ':', el primera obtendremos "trato <jugador>" y en la
        //segunda los argumentos

        ArrayList<String> separacionDosPuntos = separar(linea, ':');

        //En el comando solo deberían aparecer los ":" una única vez, generando dos strings, en caso de que haya más o menos
        //será un error.
        if(separacionDosPuntos.size() != 2){
            Output.errorComando("Comando <<trato>> incorrecto.");
            return null;
        }

        //A continuación se separa la primera componente por espacios para obtener el jugador
        //Ya no es necesario realizar la comprobación debido a que ya se ha separado antes por espacios
        aux = separar(separacionDosPuntos.get(0), ' ').get(1);

        Jugador jugador; //Ahora se va a comprobar que el jugador introducido exista.

        if((jugador = getApp().getJuego().getJugadores().get(aux)) == null){
            Output.errorComando("El jugador introducido no existe.");
            return null;
        }

        //Ahora se va a proceder a separar la segunda componente, la de los argumentos, a partir de 'y'.
        //En caso de que no haya ninguna 'y' y solo se realice un cambio habrá un elemento en el array.

        ArrayList<String> argumentosSeparados = separar(separacionDosPuntos.get(1), '+');

        for(String argumento : argumentosSeparados){

            //Se va a separar el argumento por '(' para obtener el nombre del subcomando y sus argumentos

            ArrayList<String> subComando = separar(argumento, '(');

            //En el subcomando solo debería aparecer un '(', o sea dos nuevos Strings
            if(subComando.size() != 2){
                Output.errorComando("Trato introducido incorrecto.");
                return null;
            }

            //Ahora se separa el subComando.get(0) (cambiar o noalquiler) por espacios por si tienen un espacio al inicio
            //Como el espacio va a ser al inicio, en cualquier caso se generará un Array de un único string.
            subComando.set(0, eliminarEspacioInicialFinal(subComando.get(0)));

            //A continuación se discrimina en función del subcomando introducido.
            switch(subComando.get(0)){

                case "cambiar":
                    ArrayList<Object> auxCambiar = interpretarCambiar(subComando.get(1));

                    //Se obtiene la tupla devuelta por interpretarCambiar y se añade a los arrays correspondientes.
                    //En caso de que un valor sea null es que no ha sido introducido por el usuario.
                    //Si el array devuelto es null es porque el comando es incorrecto.
                    if(auxCambiar == null){
                        return null;
                    }
                    for(int i = 0; i < auxCambiar.size(); i++){

                        if(auxCambiar.get(i) != null){
                            switch (i) {
                                case 0:
                                    propiedadDar.addAll((ArrayList)auxCambiar.get(i));
                                    break;

                                case 1:
                                    dineroDar+=(Integer) auxCambiar.get(i);
                                    break;

                                case 2:
                                    propiedadRecibir.addAll((ArrayList)auxCambiar.get(i));
                                    break;

                                case 3:
                                    dineroRecibir+=((Integer) auxCambiar.get(i));
                                    break;
                            }
                        }

                    }
                    break;

                case "noalquiler":
                    ArrayList<Object> auxNoAlquiler = interpretarNoAlquiler(subComando.get(1));
                    if(auxNoAlquiler == null){
                        return null;
                    }

                    ArrayList propiedades = (ArrayList) auxNoAlquiler.get(0);

                    //Se crea una tupla (propiedad, turno) para cada propiedad

                    for(Object propiedad : propiedades){

                        Inmunidad inmunidad = new Inmunidad((Propiedad)propiedad, (Integer)auxNoAlquiler.get(1));

                        propiedadesInmune.add(inmunidad);

                    }

                    break;


                default:
                    Output.errorComando(subComando.get(0) + " no es un subcomando de trato");
                    break;

            }

        }

        salida.add(propiedadDar);
        salida.add(dineroDar);
        salida.add(propiedadRecibir);
        salida.add(dineroRecibir);
        salida.add(propiedadesInmune);
        salida.add(jugador);

        return salida;
    }

    public void ejecutarTrato() throws NoLiquidezException, NoSerPropietarioException{

        if(!getApp().getJuego().isIniciado()){
            Output.errorComando("El juego no se ha iniciado");
            return;
        }

        Jugador emisor = getApp().getJuego().getTurno();
        Jugador receptor = null;
        ArrayList<Object> argumentos = interpretarTrato(getLinea());
        ArrayList<Propiedad> propiedadesDar, propiedadesRecibir;
        Integer dineroDar, dineroRecibir;

        if(argumentos == null){
            return;
        }

        propiedadesDar = (ArrayList<Propiedad>)argumentos.get(0);
        propiedadesRecibir = (ArrayList<Propiedad>)argumentos.get(2);
        dineroDar = (Integer) argumentos.get(1);
        dineroRecibir = (Integer) argumentos.get(3);
        receptor = (Jugador) argumentos.get(5);
        ArrayList<Inmunidad> propiedadesInmunidad = (ArrayList<Inmunidad>) argumentos.get(4);

        if(propiedadesDar == null){
            propiedadesDar = new ArrayList<>();
        }

        if(propiedadesRecibir == null){
            propiedadesRecibir = new ArrayList<>();
        }

        if(dineroDar == null){
            dineroDar = 0;
        }

        if(dineroRecibir == null){
            dineroRecibir = 0;
        }

        if(propiedadesInmunidad == null){
            propiedadesInmunidad = new ArrayList<>();
        }

        if(emisor.balanceNegativoTrasPago(dineroDar))
            throw new NoLiquidezException("El jugador " + emisor.getNombre() + " no dispone de liquidez suficiente para proponer el trato.");

        for(Propiedad propiedad : propiedadesDar){
            if(!propiedad.getPropietario().equals(emisor))
                throw new NoSerPropietarioException("La propiedad " + propiedad.getNombre() + " no pertenece a " + emisor.getNombre());

        }

        for(Propiedad propiedad : propiedadesRecibir){
            if(!propiedad.getPropietario().equals(receptor))
                throw new NoSerPropietarioException("La propiedad " + propiedad.getNombre() + " no pertenece a " + receptor.getNombre());
        }

        for(Inmunidad inmunidad : propiedadesInmunidad) {
            if (!inmunidad.getPropiedad().getPropietario().equals(receptor))
                throw new NoSerPropietarioException("La propiedad " + inmunidad.getPropiedad().getNombre() + " no pertenece a " + receptor.getNombre());
        }

        getApp().getJuego().incrementarNumTratos(1);


        String idTrato = "trato"+getApp().getJuego().getNumTratos();

        Trato trato = new Trato(emisor, receptor, propiedadesDar, propiedadesRecibir, dineroDar, dineroRecibir, propiedadesInmunidad, idTrato);

        getApp().getJuego().getTurno().getTratosEmitidos().put(trato.getId(), trato);
        receptor.proponerTrato(trato);

        Output.respuesta(Output.toArrayString(trato.toString()));

    }

    @Override
    public void aceptarTrato(String idTrato) throws NoLiquidezException, NoSerPropietarioException{
        if(!getApp().getJuego().isIniciado()){
            Output.errorComando("El juego no está iniciado");
            return;
        }
        getApp().getJuego().getTurno().aceptarTrato(idTrato);
    }

    @Override
    public void eliminarTrato(String idTrato) {
        if(!getApp().getJuego().isIniciado()){
            Output.errorComando("El juego no está iniciado");
            return;
        }
        getApp().getJuego().getTurno().eliminarTrato(idTrato);
    }

    @Override
    public void listarTrato() {
        if(!getApp().getJuego().isIniciado()){
            Output.errorComando("El juego no está iniciado");
            return;
        }

        Set<String> tratos = getApp().getJuego().getTurno().getTratosEmitidos().keySet();

        String salida = "";
        boolean emitido = false;
        boolean recibido = false;

        salida += "\n(!) Tratos emitidos pendientes: \n";

        int contador = 0;

        for(String idTrato : tratos){
            emitido = true;
            contador++;
            salida += "(Trato "+contador+")────────────────────────────────────────────()";
            salida += getApp().getJuego().getTurno().getTratosEmitidos().get(idTrato).toString();
        }

        if(!emitido)
            salida += " (-) Sin tratos emitidos.\n";

        salida += "\n(!) Tratos recibidos: \n";

        tratos = getApp().getJuego().getTurno().getTratosRecibidos().keySet();

        contador = 0;

        for(String idTrato : tratos){
            recibido = true;
            contador++;
            salida += "(Trato "+contador+")────────────────────────────────────────────()\n";
            salida += getApp().getJuego().getTurno().getTratosRecibidos().get(idTrato).toString();
        }

        if(!recibido)
            salida += " (-) Sin tratos recibidos.\n";

        Output.respuesta(Output.toArrayString(salida));
    }
}

