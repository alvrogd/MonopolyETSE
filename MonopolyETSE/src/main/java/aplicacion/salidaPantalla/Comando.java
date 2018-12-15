package aplicacion.salidaPantalla;

import aplicacion.Aplicacion;
import aplicacion.TipoComando;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.TipoEdificio;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.cartas.Carta;
import monopoly.tablero.jerarquiaCasillas.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Comando implements IComando {

    private ArrayList<String> argv;
    private Aplicacion app;

    //todo toString de las casillas

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

        this.argv = new ArrayList<>();

        this.app = app;

        separarEspacios(linea);

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
        for (int i = 0; arrayChar[i] != ' '; i++) {

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
            numEspacios = tipoComando.getEspaciosCasilla() - 1; //-1 porque ya se ha contado un espacio
        }
        //Se siguen buscando las palabras, se empieza en comienzo+1 (no se cuenta el anterior espacio
        for (int i = comienzo + 1; i < tamChar; i++) {

            if (arrayChar[i] == ' ') {

                if (numEspacios != 0) {
                    numEspacios--;
                    argumentos.add(aux);
                    aux = "";
                }

            } else {
                aux += arrayChar[i];
            }
        }

        return argumentos;

    }

    public ArrayList<String> getArgv() {
        return argv;
    }

    public Aplicacion getApp() {
        return app;
    }

    public void ejecutarComando() {

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
                        crearJugador(getArgv().get(3), getArgv().get(4));
                        break;

                    default:
                        Output.errorComando("Argumentos del comando -crear jugador- incorrectos.");

                }

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
                            listarEdificiosGrupo(getArgv().get(3));

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

            case "mover":
                if(size > 1)
                    mover(getArgv().get(1));

        }

    }

    private void mover(String aumento){

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

    private void ejecutarSuerte(){

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getApp().getJuego().getCartasSuerte().size();

        for(Carta carta : getApp().getJuego().getCartasSuerte()){

            System.out.println("("+count+") "+carta.toString());
            count++;

        }

        System.out.println("(*) Opción: ");
        opc = entrada.nextInt();

        getApp().getJuego().getTurno().leerCarta(getApp().getJuego().getCartasSuerte().get(opc%size));

    }

    private void ejecutarComunidad(){

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getApp().getJuego().getCartasComunidad().size();

        for(Carta carta : getApp().getJuego().getCartasComunidad()){

            System.out.println("("+count+") "+carta.toString());
            count++;

        }

        System.out.println("(*) Opción: ");
        opc = entrada.nextInt();

        getApp().getJuego().getTurno().leerCarta(getApp().getJuego().getCartasComunidad().get(opc%size));

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

        if (avatar == null) {

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

        auxiliar = Output.JugadortoArrayString(getApp().getJuego().getTurno());

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
            infoEnviar = Output.JugadortoArrayString(getApp().getJuego().getJugador(jug));

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

            avatares = Output.AvatartoArrayString(getApp().getJuego().getJugador(nombre).getAvatar());

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
                            respuesta.addAll(Output.EdificiotoArrayString(edificio));
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
        int numCasillas = grupo.getSolares().size();

        res.add("Los edificios del grupo " + tipoGrupo.toString() + " son los siguientes.");

        boolean flagGrupo = false;

        for (Casilla casilla : grupo.getSolares()) {

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

                        //todo ponlo bonito
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

    public void lanzarDados() {

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        if (getApp().getJuego().isHaLanzadoDados()) {
            Output.errorComando("Ya has lanzado los dados.");
            return;
        }

        if (getApp().getJuego().getTurno().getAvatar().getTipo() != TipoAvatar.coche)
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

    public void salirCarcel() {

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

        Output.respuesta(Output.CasillatoArrayString(casilla));
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

        Output.respuesta(Output.JugadortoArrayString(jugador));
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
        Output.respuesta(Output.AvatartoArrayString(avatar1));
    }

    public void comprar(String nombreSolar) {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        if (getApp().getJuego().isHaCompradoPropiedad() && getApp().getJuego().getTurno().getAvatar().getTipo().equals(TipoAvatar.coche) &&
                !getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
            Output.sugerencia("Ya has comprado una casilla en este turno.");
            return;
        }

        Casilla casilla1 = getApp().getJuego().getTablero().getCasillasTablero().get((nombreSolar));

        if (casilla1 == null) {
            Output.errorComando("Esa casilla no existe.");
            return;
        }

        getApp().getJuego().getTurno().comprar(getApp().getJuego().getBanca(), casilla1);
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

                        auxiliar1 = Output.CasillatoArrayString(casillaAux);
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

    public void edificar(String tipoEdificio) {
        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        TipoEdificio edificio = TipoEdificio.toEdificio(tipoEdificio);

        if (edificio != null)
            getApp().getJuego().getTurno().crearEdificio(edificio);
        else
            Output.errorComando("Opción del comando -edificar- incorrecta, tipo de edificio incorrecto.");
    }

    public void cambiarModo() {
        boolean isEstandar;

        if (!getApp().getJuego().isIniciado()) {
            Output.errorComando("El juego no se ha iniciado.");
            return;
        }

        getApp().getJuego().getTurno().getAvatar().switchMovimiento();
    }

    public void hipotecar(String nombrePropiedad) {
        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get(nombrePropiedad);

        if (casilla == null) {
            Output.errorComando("La casilla " + nombrePropiedad + " no existe.");
            return;
        }

        getApp().getJuego().getTurno().hipotecar(casilla);
    }

    public void deshipotecar(String nombrePropiedad) {
        Casilla casilla = getApp().getJuego().getTablero().getCasillasTablero().get(nombrePropiedad);

        if (casilla == null) {
            Output.errorComando("La casilla " + nombrePropiedad + " no existe.");
            return;
        }

        getApp().getJuego().getTurno().deshipotecar(casilla);
    }

    public void vender(String tipoEdificio, String numero, String nombrePropiedad) {
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

        int cantidad = Integer.parseInt(numero);

        getApp().getJuego().getTurno().venderEdificio(edificio, cantidad, casillaV);
    }

    public void avanzar() {
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

}

