package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TipoColor;
import monopoly.Juego;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Aplicacion {

    private Juego juego;

    public Aplicacion() {

        juego = new Juego();

    }

    public Juego getJuego(){
        return juego;
    }

    public void introducirComando(String entrada) {

        String linea;
        ArrayList<Object> toComando;

        if (juego.getTurno() == null) {
            System.err.println("Juego no iniciado.");
            return;
        }

        if((toComando = toComando(entrada)) == null){
            return;
        }

    }

    //Función que devuelve la tupla (TipoComando, String Argumentos), si se le pasa una línea devuelve la información
    //separada en el comando y en sus correspondientes argumentos.

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
                if (argc < 3) {
                    Output.errorComando("Opción del comando -crear- incorrecta.");
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
                if (argc < 3) {
                    Output.errorComando("Opción del comando -comprar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch (comando.get(1)) {
                    default:
                        salida.add(TipoComando.comprarPropiedad);
                        salida.add(comando.get(1));
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

            default:
                Output.errorComando("Comando incorrecto.");
                salida.add(null);
                break;

        }

        return salida;
    }

    //Función que interpreta la tupla devuelta por toComando realizando las acciones específicas del comando.

    private void interpretarComando(ArrayList<Object> comando) {

        //comando -> posicion 0: TipoComando; posicion 1: String con los argumentos

        if(comando == null){
            System.err.println("Comando referencia a null.");
            return;
        }

        if ((TipoComando) comando.get(0) == TipoComando.crearJugador) {
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


        } else if((TipoComando) comando.get(0) == TipoComando.turno){

            if(!juego.isIniciado()){
                Output.errorComando("El juego no se ha iniciado.");
                return;
            }

            Output.respuesta("El jugador que tiene el turno es: "+juego.getTurno().getNombre());

        } else if((TipoComando) comando.get(0) == TipoComando.listarJugadores){

            ArrayList<String> respuesta = new ArrayList<>();
            ArrayList<String> jugadores;

            if(!juego.isIniciado()){
                Output.errorComando("El juego no se ha iniciado.");
                return;
            }

            jugadores = juego.getNombresJugadores();

            respuesta.add("Los jugadores que participan en el juego son, con su respectivo orden: ");

            for(String jugador:jugadores){
                respuesta.add("      -> "+jugador);
            }

            Output.respuesta(respuesta);

        } else if((TipoComando) comando.get(0) == TipoComando.listarAvatares){

            ArrayList<String> respuesta = new ArrayList<>();
            ArrayList<String> nombresJugadores;
            HashMap<String, Jugador> jugadores;

            if(!juego.isIniciado()){
                Output.errorComando("El juego no se ha iniciado.");
                return;
            }

            jugadores = juego.getJugadores();
            nombresJugadores = juego.getNombresJugadores();

            respuesta.add("Los jugadores que están en el tablero son, con su respectivo orden: ");

            Avatar avatarAux;
            for(String jugador:nombresJugadores){
                avatarAux = jugadores.get(jugador).getAvatar();
                respuesta.add("      -> ID: "+ avatarAux.getIdentificador());
            }

            Output.respuesta(respuesta);

        }
    }

}


