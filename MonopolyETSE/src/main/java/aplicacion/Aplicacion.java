package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;
import aplicacion.salidaPantalla.TipoColor;
import monopoly.Juego;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Aplicacion {

    private Juego juego;
    private Menu menu;
    private ArrayList<ArrayList<Object>> buffer;
    private boolean haLanzadoDados;

    /*Constructores*/

    /**
     * Constructor que crea el juego, el menú y el buffer.
     */
    public Aplicacion() {

        juego = new Juego();
        menu = new Menu(this);
        buffer = new ArrayList<>();

        Output.setBuffer(buffer);

    }

    /*Getters*/
    public Juego getJuego() {
        return juego;
    }

    public Menu getMenu() {
        return menu;
    }

    public ArrayList<ArrayList<Object>> getBuffer() {
        return buffer;
    }

    public boolean isHaLanzadoDados() {
        return haLanzadoDados;
    }

    /*Métodos*/

    /**
     * Función para imprimir el buffer guardado en Output
     */
    public void imprimirBuffer() {

        //En caso de que el buffer se vaya a imprimir en el trablero y el juego esté iniciado
        if (Output.isImpresionTablero() && getJuego().isIniciado()) {
            System.out.print("\033[H\033[2J"); //se limpia la consola

            //Se imprime el tablero junto el buffer
            System.out.println(TableroASCII.pintaTablero(getJuego().getTablero(), Output.getBuffer()));
            Output.vaciarBuffer();

        } else {
            //En caso contrario se comprueba si el juego está iniciado
            int tam = Output.getBuffer().size();

            if (getJuego().isIniciado()) {
                System.out.print("\033[H\033[2J");
                //Si está iniciado se imprime el tablero sin nada en su interior
                System.out.println(TableroASCII.pintaTablero(getJuego().getTablero(), null));
            }

            //Se imprime el buffer
            for (int i = 0; i < tam; i++) {
                System.out.println(Output.getBuffer().get(i).get(0));
            }

            //Se vacía el buffer
            Output.vaciarBuffer();
        }

    }

    /**
     * Función para limpiar el tablero
     */
    public void limpiarTablero() {

        for (int i = 0; i < 50; i++)
            System.out.println();

    }

    /**
     * Función para introducir el comando y que realiza su acción específica
     *
     * @param entrada se le pasa el comando entero en un String
     */
    public void introducirComando(String entrada) {

        String linea;
        ArrayList<Object> toComando;

        if (juego.getTurno() == null) {
            System.err.println("Turno referencia a null.");
            return;
        }

        if ((toComando = toComando(entrada)) == null) {
            imprimirBuffer();
            return;
        } else if (toComando.get(0) == null) {
            imprimirBuffer();
            return;
        }
        interpretarComando(toComando);

        imprimirBuffer();

        if (getJuego().isFinalizado())
            System.exit(0);

    }

    //Función que devuelve la tupla (TipoComando, String Argumentos), si se le pasa una línea devuelve la información
    //separada en el comando y en sus correspondientes argumentos.

    /**
     * Función que devuelve la tupla (TipoComando, String Argumentos), si se le pasa una línea devuelve la información
     * separada en el comando y en sus correspondientes argumentos.
     *
     * @param linea comando a introducir
     */
    private ArrayList<Object> toComando(String linea) {

        ArrayList<Object> salida = new ArrayList<>();

        char[] cadena = linea.toCharArray();
        int tam = linea.length();

        ArrayList<String> comando = new ArrayList<>();
        String aux = "";
        int argc;

        //Con contador se cuenta el número de palabras que se han separado
        int contador = 0, i;

        //Se separa la línea por comando y por argumentos

        for (i = 0; i < tam; i++) {

            //Los comandos a tratar tienen como mucho dos palabras por eso contador < 2
            if (cadena[i] == ' ' && contador < 2) {
                comando.add(aux);
                aux = "";
                contador++;
            } else {
                aux += cadena[i];
            }

        }
        comando.add(aux);

        argc = comando.size();

        switch (comando.get(0)) {
            case "crear":
                //Crear tiene que tener más de dos argumentos
                if (argc < 3) {
                    Output.errorComando("Opción del comando -crear- incorrecta.");
                    imprimirBuffer();
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "jugador":
                        salida.add(TipoComando.crearJugador);
                        salida.add(comando.get(2));
                        break;

                    default:
                        Output.errorComando("Opción del comando -crear- incorrecta.");
                        imprimirBuffer();
                        salida.add(null);
                        break;
                }
                break;

            case "jugador":
                salida.add(TipoComando.turno);
                break;

            case "listar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -listar- incorrecta");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "jugadores":
                        salida.add(TipoComando.listarJugadores);
                        break;

                    case "avatares":
                        salida.add(TipoComando.listarAvatares);
                        break;

                    case "enventa":
                        salida.add(TipoComando.listarVentas);
                        break;

                    case "edificios":
                        salida.add(TipoComando.listarEdificios);
                        if (argc >= 3)
                            salida.add(comando.get(2));
                        break;

                    default:
                        Output.errorComando("Opción del comando -listar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "lanzar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -lanzar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "dados":
                        salida.add(TipoComando.lanzarDados);
                        break;

                    default:
                        Output.errorComando("Opción del comando -lanzar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "acabar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -acabar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "turno":
                        salida.add(TipoComando.finalizarTurno);
                        break;

                    default:
                        Output.errorComando("Opción del comando -acabar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "salir":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -salir- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "carcel":
                        salida.add(TipoComando.salirCarcel);
                        break;

                    default:
                        Output.errorComando("Opción del comando -salir- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "describir":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -describir- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "jugador":
                        if (argc < 3) {
                            System.err.println("Opción del comando -describir- incorrecta.");
                            salida.add(null);
                            break;
                        }
                        salida.add(TipoComando.describirJugador);
                        salida.add(comando.get(2));
                        break;

                    case "avatar":
                        if (argc < 3) {
                            Output.errorComando("Opción del comando -describir- incorrecta.");
                            salida.add(null);
                            break;
                        }
                        salida.add(TipoComando.describirAvatar);
                        salida.add(comando.get(2));
                        break;

                    default:
                        salida.add(TipoComando.describirCasilla);
                        if (argc < 3) {
                            salida.add(comando.get(1));
                        } else {
                            salida.add(comando.get(1) + " " + comando.get(2));
                        }
                        break;
                }
                break;

            case "comprar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -comprar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    default:
                        salida.add(TipoComando.comprarPropiedad);

                        if (argc < 3) {
                            salida.add(comando.get(1));
                        } else {
                            salida.add(comando.get(1) + " " + comando.get(2));
                        }

                        break;
                }
                break;

            case "ver":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -ver- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "tablero":
                        salida.add(TipoComando.verTablero);
                        break;

                    default:
                        Output.errorComando("Opción del comando -ver- incorrecta.");
                        break;
                }
                break;

            case "ayuda":
                salida.add(TipoComando.ayuda);
                break;

            case "iniciar":
                salida.add(TipoComando.iniciarJuego);
                break;

            case "mover":
                getJuego().getTurno().getAvatar().mover(Integer.parseInt((String) comando.get(1)), true);
                salida.add(null);
                break;

            case "pagar":
                getJuego().getTurno().pagar(getJuego().getBanca(), Integer.parseInt((String) comando.get(1)));
                salida.add(null);
                break;

            case "edificar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -edificar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "casa":
                        salida.add(TipoComando.edificar);
                        salida.add(TipoEdificio.valueOf("casa"));
                        break;

                    case "hotel":
                        salida.add(TipoComando.edificar);
                        salida.add(TipoEdificio.valueOf("hotel"));
                        break;

                    case "piscina":
                        salida.add(TipoComando.edificar);
                        salida.add(TipoEdificio.valueOf("piscina"));
                        break;

                    case "pista":
                        salida.add(TipoComando.edificar);
                        salida.add(TipoEdificio.valueOf("pistaDeporte"));
                        break;

                    default:
                        Output.errorComando("Opción del comando -edificar- incorrecta.");
                        Output.sugerencia("Edificios que se pueden construir: ",
                                "     -> Casa",
                                "     -> Hotel",
                                "     -> Piscina",
                                "     -> Pista de deporte");
                        salida.add(null);

                }
                break;

            case "cambiar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -cambiar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    case "modo":
                        salida.add(TipoComando.cambiarModo);
                        break;

                    default:
                        Output.errorComando("Opción del comando -cambiar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "vender":
                if (argc < 3) {
                    Output.errorComando("Opción del comando -vender- incorrecta.");
                    salida.add(null);
                    break;
                }

                int length = comando.get(2).length();
                char[] argv = comando.get(2).toCharArray();
                String auxiliar = "";

                int count = 0;
                for (i = 0; i < length; i++) {

                    if (argv[i] == ' ' && count == 0) {
                        comando.add(auxiliar);
                        auxiliar = "";
                        count++;
                    } else {
                        auxiliar += argv[i];
                    }

                }
                comando.add(auxiliar);
                switch (comando.get(1)) {
                    case "casas":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.casa);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "hoteles":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.hotel);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "piscina":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.piscina);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "pista":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.pistaDeporte);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "piscinas":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.piscina);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "hotel":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.hotel);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "casa":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.casa);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    case "pistas":
                        salida.add(TipoComando.vender);
                        salida.add(TipoEdificio.pistaDeporte);
                        salida.add(Integer.parseInt(comando.get(3)));
                        salida.add(comando.get(4));
                        break;
                    default:
                        Output.errorComando("Opción del comando -vender- incorrecta.");
                        salida.add(null);
                        break;
                }

                break;

            case "hipotecar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -hipotecar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    default:
                        salida.add(TipoComando.hipotecar);

                        if (argc < 3) {
                            salida.add(comando.get(1));
                        } else {
                            salida.add(comando.get(1) + " " + comando.get(2));
                        }
                        break;
                }
                break;

            case "deshipotecar":
                if (argc < 2) {
                    Output.errorComando("Opción del comando -deshipotecar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    default:
                        salida.add(TipoComando.deshipotecar);

                        if (argc < 3) {
                            salida.add(comando.get(1));
                        } else {
                            salida.add(comando.get(1) + " " + comando.get(2));
                        }
                        break;
                }
                break;

            case "avanzar":
                salida.add(TipoComando.avanzar);
                break;

            case "estadisticas":
                if(argc < 2){
                    salida.add(TipoComando.estadisticasGlobales);
                    break;
                }

                salida.add(TipoComando.estadisticasJugador);
                salida.add(comando.get(1));
                break;

            default:
                Output.errorComando("Comando incorrecto.");
                salida.add(null);
                break;

        }

        return salida;
    }

    private void listarEdificiosGrupo(TipoGrupo tipoGrupo) {

        Grupo grupo = getJuego().getTablero().getGrupos().get(tipoGrupo);

        //ArrayList de String donde se almacenará la respuesta.
        ArrayList<String> res = new ArrayList<>();

        //ArrayList auxiliar para almacenar un ArrayList de edificios obtenido a partir de un HashMap
        ArrayList<Edificio> auxEdificios;

        //StringBuilder que se utiliza para ir generando el mensaje para cada edificio
        StringBuilder idEdificios;

        //Número de casillas que tiene el grupo
        int numCasillas = grupo.getCasillas().size();

        res.add("Los edificios del grupo " + tipoGrupo.toString() + " son los siguientes.");

        boolean flagGrupo = false;

        for (Casilla casilla : grupo.getCasillas()) {

            if(!casilla.isComprable()){

                flagGrupo = true;

                res.add("(*) Propiedad: " + casilla.getNombre());

                for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                    //Variable para contar número de edificios que hay en cada casilla
                    int numEdificio = 0;

                    //Variable donde se almacenará el número de edificios del tipo actual que se pueden construir
                    int construcciones;

                    idEdificios = new StringBuilder();

                    idEdificios.append("    -> ").append(tipoEdificio.getPlural()).append(tipoEdificio.getEspacios()).append(": {");

                    //Se obtienen los edificios contenido del tipo actual.
                    auxEdificios = casilla.getEdificiosContenidos().get(tipoEdificio);

                    for (Edificio edificio : auxEdificios) {

                        idEdificios.append(edificio.getId());
                        idEdificios.append(",");

                        numEdificio++;

                    } // Fin del for de edificios

                    if (numEdificio == 0) {
                        idEdificios.append("No hay ").append(tipoEdificio.getPlural()).append("}");
                    } else {
                        int length = idEdificios.length();
                        idEdificios.replace(length-1, length-1, "}");
                    }

                    if (tipoEdificio.equals(TipoEdificio.casa) &&
                            (casilla.getEdificiosContenidos().get(TipoEdificio.hotel).size() != numCasillas)){
                        construcciones = 4 - numEdificio;

                    } else {
                        construcciones = numCasillas - numEdificio;
                    }

                    //todo ponlo bonito
                    if(construcciones == 0){
                        idEdificios.append(" | No se pueden construir más ").append(tipoEdificio.getPlural());
                    }
                    idEdificios.append(" | Se pueden construir ").append(construcciones).append(" ").append(tipoEdificio.getPlural());

                    res.add(idEdificios.toString());

                } // Fin del for de tipoEdificio

                res.add("    -> Alquiler: " + casilla.getAlquiler());
                res.add(" ");
            } // Fin del if para ver si es comprable

        } // Fin del bucle de casillas

        if(!flagGrupo){

            res.add("(!) No hay edificios en este grupo.");

        }

        Output.respuesta(res);

    }

    /**
     * Función que interpreta la tupla devuelta por toComando realizando las acciones específicas del comando.
     *
     * @param comando tupla devuelta por toComando
     */
    private void interpretarComando(ArrayList<Object> comando) {

        //comando -> posicion 0: TipoComando; posicion 1: String con los argumentos

        if (comando == null) {
            System.err.println("Comando referencia a null.");
            return;
        }

        if (comando.get(0) == null) {
            System.err.println("No existe el comando.");
        }

        switch ((TipoComando) comando.get(0)) {
            case crearJugador:
                String aux = "";

                //Se pasan los argumentos a un array de caracteres
                char[] argc = ((String) comando.get(1)).toCharArray();

                //Longitud del String
                int tamArg = ((String) comando.get(1)).length();

                //En argumentoSeparados se irán añadiendo los argumentos separados por palabras
                ArrayList<String> argumentoSeparados = new ArrayList<>();

                //Se comprueba que el juego no esté iniciado.
                if (juego.isIniciado()) {

                    Output.errorComando("El juego ya está iniciado. No se pueden añadir más jugadores.");
                    return;

                }

                if (juego.getNombresJugadores().size() == 6) {
                    Output.errorComando("Has alcanzado el número máximo de jugadores");
                    return;
                }

                //Se recorre el array de caracteres
                for (int i = 0; i < tamArg; i++) {

                    if (argc[i] == ' ') {
                        //Si se ha llegado a un espacio se mete la palabra en el ArrayList argumentoSeparados
                        argumentoSeparados.add(aux);
                        aux = "";
                    } else {
                        //Se va creando la palabra añadiendo los caracteres a aux
                        aux += argc[i];
                    }

                }

                //Al salir del bucle la última palabra también se añade al ArrayList.
                argumentoSeparados.add(aux);

                //Se comprueba que haya al menos dos argumentos, ya que hay que introducir nombre de usuario y el avatar
                if (argumentoSeparados.size() < 2) {

                    //Se manda un mensaje de error y finaliza
                    Output.errorComando("Introduzca el avatar después del nombre en la opción «crear»");
                    return;
                }

                if (juego.getNombresJugadores().contains((String) argumentoSeparados.get(0))) {
                    Output.errorComando("Ese jugador ya pertenece al juego.");
                    return;
                }

                //Con la función toAvatar se pasa el String donde está el avatar a TipoAvatar, en caso de que no exista el
                //tipo avatar será igual a null.
                TipoAvatar avatar = TipoAvatar.toAvatar((String) argumentoSeparados.get(1));

                //Se comprueba que se ha introducido bien el tipo de avatar
                if (avatar == null) {

                    //En caso de que el avatar sea incorrecta se manda un error al usuario y una sugerencia con los diversos
                    //tipos de avatares
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

                //Si todas las comprobaciones han ido bien, se creará el jugador con sus parámetros y como casilla inicial
                //la salida.
                Jugador jugador = new Jugador((String) argumentoSeparados.get(0), juego.getTablero(),
                        avatar, juego.getTablero().getCasillas().get(0).get(0));

                //Se añade el jugador al juego
                juego.addJugador(jugador);

                Output.respuesta("¡Jugador creado!",
                        "        -> Nombre: " + jugador.getNombre(),
                        "        -> Avatar: " + jugador.getAvatar().getIdentificador());
                break;

            case turno:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                ArrayList<String> auxiliar = new ArrayList<>();
                ArrayList<String> informacionEnviar = new ArrayList<>();

                auxiliar = Output.JugadortoArrayString(juego.getTurno());

                informacionEnviar.add("Información del jugador que tiene el turno.");

                //Elimino la información de propiedades y propiedades hipotecadas ya que no es necesaria
                for (int i = 0; i < 2; i++) {
                    informacionEnviar.add(auxiliar.get(i));
                }

                Output.respuesta(informacionEnviar);
                break;

            case listarJugadores:
                //Listar jugadores, en respuesta se añade la información que se pasará a output.
                ArrayList<String> respuesta = new ArrayList<>();

                //Se almacenan los nombres de los jugadores
                ArrayList<String> jugadores;

                //Array donde está la información recibida de JugadorToArrayString
                ArrayList<String> infoEnviar;

                //Se comprueba si el juego se ha iniciado o no
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                jugadores = juego.getNombresJugadores();

                //1ª línea del mensaje
                respuesta.add("Los jugadores que participan en el juego son, con su respectivo orden: ");

                //Variable para guardar el tamaño del array recibido por JugadorToArrayString
                int tam;

                for (String jug : jugadores) {
                    //Se obtiene la información del jugador con la función JugadortoArrayString
                    infoEnviar = Output.JugadortoArrayString(juego.getJugador(jug));

                    //Nueva línea
                    respuesta.add("");

                    //Se añade el nombre del jugador

                    tam = infoEnviar.size();

                    //Se imprime la información de infoEnviar, exceptuando el nombre del jugador, por eso j = 1.
                    for (int j = 0; j < tam; j++)
                        respuesta.add(infoEnviar.get(j));
                }

                Output.respuesta(respuesta);
                break;

            case listarAvatares:
                ArrayList<String> resp = new ArrayList<>();
                ArrayList<String> jug;
                ArrayList<String> avatares;

                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                jug = juego.getNombresJugadores();

                int taman;

                resp.add("Los avatares que hay en el tablero son los siguientes en su correspondiente orden:");

                for (String nombre : jug) {

                    avatares = Output.AvatartoArrayString(juego.getJugador(nombre).getAvatar());

                    resp.add("");

                    tam = avatares.size();
                    for (int i = 0; i < tam; i++) {
                        resp.add(avatares.get(i));
                    }

                }

                Output.respuesta(resp);
                break;

            case listarEdificios:

                ArrayList<String> res = new ArrayList<>();
                Grupo grupo = null;

                if (comando.size() == 2) {

                    TipoGrupo tipoGrupo;
                    switch (comando.get(1).toString()) {

                        case "negro":
                            tipoGrupo = TipoGrupo.negro;
                            break;

                        case "cyan":
                            tipoGrupo = TipoGrupo.cyan;
                            break;

                        case "rosa":
                            tipoGrupo = TipoGrupo.rosa;
                            break;

                        case "naranja":
                            tipoGrupo = TipoGrupo.naranja;
                            break;

                        case "rojo":
                            tipoGrupo = TipoGrupo.rojo;
                            break;

                        case "marron":
                            tipoGrupo = TipoGrupo.marron;
                            break;

                        case "verde":
                            tipoGrupo = TipoGrupo.verde;
                            break;

                        case "azul":
                            tipoGrupo = TipoGrupo.azul;
                            break;

                        default:
                            Output.respuesta("Ese grupo no existe.");
                            return;
                    }

                    listarEdificiosGrupo(tipoGrupo);

                } else {

                    boolean flag = false;

                    res.add("Los edificios del tablero son los siguientes.");

                    for (TipoGrupo tipoGrupo : TipoGrupo.values()) {

                        grupo = getJuego().getTablero().getGrupos().get(tipoGrupo);

                        for (Casilla casilla : grupo.getCasillas()) {

                            for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                                for (Edificio edificio : casilla.getEdificiosContenidos().get(tipoEdificio)) {

                                    flag = true;
                                    res.addAll(Output.EdificiotoArrayString(edificio));
                                    res.add(" ");

                                }
                            }
                        }
                    }

                    if(!flag){

                        res.add("(!) No hay edificios en el tablero.");

                    }

                    Output.respuesta(res);

                }


                break;

            case lanzarDados:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                if (juego.isHaLanzadoDados()) {
                    Output.errorComando("Ya has lanzado los dados.");
                    return;
                }

                if (juego.getTurno().getAvatar().getTipo() != TipoAvatar.coche)
                    getJuego().setHaCompradoPropiedad(false);

                juego.getTurno().lanzarDados(juego.getTablero().getDado());
                break;

            case finalizarTurno:

                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }
                if (!juego.isHaLanzadoDados()) {
                    Output.errorComando("¡No ha lanzado los dados!");
                    return;
                }

                juego.finalizarTurno();

                Output.respuesta("El jugador actual es " + juego.getTurno().getNombre());
                break;

            case salirCarcel:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                juego.getTurno().getAvatar().salirCarcel();
                break;

            case describirCasilla:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                Casilla casilla = juego.getTablero().getCasillasTablero().get((String) comando.get(1));

                if (casilla == null) {
                    Output.errorComando("La casilla indicada no existe.");
                    return;
                }

                Output.respuesta(Output.CasillatoArrayString(casilla));
                break;

            case describirJugador:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                Jugador jugador1 = juego.getJugadores().get(comando.get(1));

                if (jugador1 == null) {
                    Output.errorComando("Ese jugador no existe.");
                    return;
                }

                Output.respuesta(Output.JugadortoArrayString(jugador1));
                break;

            case describirAvatar:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                Avatar avatar1 = juego.getTablero().getAvataresContenidos().get((((String) comando.get(1)).charAt(0)));

                if (avatar1 == null) {
                    Output.errorComando("Ese avatar no existe.");
                    return;
                }
                Output.respuesta(Output.AvatartoArrayString(avatar1));
                break;

            case comprarPropiedad:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                if (juego.isHaCompradoPropiedad() && getJuego().getTurno().getAvatar().getTipo().equals(TipoAvatar.coche) &&
                        !getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
                    Output.sugerencia("Ya has comprado una casilla en este turno.");
                    return;
                }

                Casilla casilla1 = juego.getTablero().getCasillasTablero().get((String) comando.get(1));

                if (casilla1 == null) {
                    Output.errorComando("Esa casilla no existe.");
                    return;
                }

                juego.getTurno().comprar(juego.getBanca(), casilla1);
                break;

            case listarVentas:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                ArrayList<String> respuesta1 = new ArrayList<>();

                ArrayList<String> auxiliar1;

                int tamArray;

                for (ArrayList<Casilla> fila : juego.getTablero().getCasillas()) {
                    for (Casilla casillaAux : fila) {
                        if (casillaAux.isComprable()) {
                            auxiliar1 = Output.CasillatoArrayString(casillaAux);
                            tamArray = auxiliar1.size();
                            respuesta1.add("");
                            for (int i = 0; i < tamArray; i++) {
                                respuesta1.add(auxiliar1.get(i));
                            }
                        }
                    }
                }

                Output.respuesta(respuesta1);
                break;

            case verTablero:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }
                break;

            case iniciarJuego:
                if (juego.isIniciado()) {
                    Output.errorComando("El juego ya está iniciado.");
                    return;
                }
                if (juego.getNombresJugadores().size() < 2) {
                    Output.errorComando("No hay suficientes jugadores para empezar el juego");
                    return;
                }
                ArrayList<String> aux1 = new ArrayList<>();

                aux1.add("¡Se ha iniciado el juego!");
                Output.imprimirRecuadro(aux1, "Información: ", TipoColor.verdeANSI, 3, 1);
                juego.iniciarJuego();
                break;

            case ayuda:
                Output.imprimirAyuda();
                break;

            case edificar:
                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                getJuego().getTurno().crearEdificio((TipoEdificio) comando.get(1));
                break;

            case cambiarModo:

                boolean isEstandar;

                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                getJuego().getTurno().getAvatar().switchMovimiento();

                break;

            case vender:

                if (!juego.isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }

                if (comando.get(2) == null) {
                    System.err.println("Introducción comando vender incorrecta");
                    return;
                }

                if (comando.get(3) == null) {
                    System.err.println("Introducción comando vender incorrecta");
                    return;
                }

                TipoEdificio edificio = (TipoEdificio) comando.get(1);
                Casilla casillaV = getJuego().getTablero().getCasillasTablero().get(comando.get(3).toString());
                int cantidad = (Integer) comando.get(2);

                getJuego().getTurno().venderEdificio(edificio, cantidad, casillaV);

                break;

            case avanzar:
                int casillasPorMoverse = getJuego().getTurno().getAvatar().getCasillasRestantesPorMoverse();

                if (!getJuego().isIniciado()) {
                    Output.errorComando("El juego no se ha iniciado.");
                    return;
                }
                if (casillasPorMoverse <= 0) {
                    Output.mensaje("No te quedan casillas por moverte, vuelve a tirar los dados.");
                    return;
                }

                getJuego().getTurno().getAvatar().avanzar(casillasPorMoverse);
                break;

            case hipotecar:
                getJuego().getTurno().hipotecar(getJuego().getTablero().getCasillasTablero().get(comando.get(1).toString()));
                break;

            case deshipotecar:
                getJuego().getTurno().deshipotecar(getJuego().getTablero().getCasillasTablero().get(comando.get(1).toString()));
                break;

            case estadisticasJugador:

                if(!getJuego().isIniciado()){
                    Output.errorComando("No se ha iniciado el juego.");
                    return;
                }

                Jugador auxJugador;

                auxJugador = getJuego().getJugadores().get(comando.get(1).toString());

                if(auxJugador == null){
                    Output.errorComando("No existe el jugador.");
                    return;
                }

                estadisticasJugador(auxJugador);

                break;

            case estadisticasGlobales:
                
                if(!getJuego().isIniciado()){
                    Output.errorComando("No se ha iniciado el juego.");
                    return;
                }

                estadisticasGlobales();
                break;
        }
    }



    private void estadisticasJugador(Jugador jugador){

        Output.respuesta("(*) Estadísticas de "+jugador.getNombre(),
                "      -> Dinero invertido           : " + jugador.getDineroInvertido(),
                "      -> Pago tasas e impuestos     : " + jugador.getPagoTasasEImpuestos(),
                "      -> Pago de alquileres         : " + jugador.getPagoDeAlquileres(),
                "      -> Cobro de alquileres        : " + jugador.getCobroDeAlquileres(),
                "      -> Pasar por casilla de salida: " + jugador.getPasarPorCasillaDeSalida(),
                "      -> Premios inversiones o botes: " + jugador.getPremiosInversionesOBote(),
                "      -> Veces en la carcel         : " + jugador.getVecesEnLaCarcel());

        return;
    }

    private void estadisticasGlobales(){

        Output.respuesta("(*) Estadísticas globales",
                "      -> Casilla más rentable    : " + getJuego().casillaMasRentable().getNombre(),
                "      -> Grupo más rentable      : " + getJuego().grupoMasRentable().getTipo().toString(),
                "      -> Casilla más frecuentada : " + getJuego().casillaMasFrecuentada().getNombre(),
                "      -> Jugador con más vueltas : " + getJuego().jugadorMasVueltas().getNombre(),
                "      -> Jugador con más tiradas : " + getJuego().jugadorMasVecesDados().getNombre(),
                "      -> Jugador en cabeza       : " + getJuego().jugadorEnCabeza().getNombre());

    }


}


