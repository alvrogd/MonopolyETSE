package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TipoColor;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {

    private Juego juego;

    public Aplicacion(){

        juego = new Juego();

    }

    public void introducirComando(){

        Scanner entrada = new Scanner(System.in);
        String linea;

        if(juego.getTurno() == null){
            System.err.println("Juego no iniciado.");
            return;
        }

        Output.imprimirCabeceraJugador(juego.getTurno());
        Output.imprimirEntradaComando();

        //linea = entrada.nextLine();

        interpretarComando(toComando("crear jugador Francisco coche"));

        //System.out.println(juego.getJugadores().get("Francisco").getNombre());
        //System.out.println(juego.getJugadores().get("Francisco").getAvatar().getTipo());

    }

    private ArrayList<Object> toComando(String linea){

        ArrayList<Object> salida = new ArrayList<>();

        char[] cadena = linea.toCharArray();
        int tam = linea.length();

        ArrayList<String> comando = new ArrayList<>();
        String aux = "";
        int argc;

        int contador = 0, i;

        for(i = 0; i < tam; i++){

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

        switch(comando.get(0)){
            case "crear":
                if(argc < 3){
                    //todo output errores
                    Output.errorComando("Opción del comando -crear- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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
                if(argc < 2){
                    Output.errorComando("Opción del comando -listar- incorrecta");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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
                if(argc < 2){
                    Output.errorComando("Opción del comando -lanzar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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
                if(argc < 2){
                    Output.errorComando("Opción del comando -acabar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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
                if(argc < 2){
                    Output.errorComando("Opción del comando -salir- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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
                if(argc < 2){
                    Output.errorComando("Opción del comando -describir- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "jugador":
                        if(argc < 3){
                            System.err.println("Opción del comando -describir- incorrecta.");
                            salida.add(null);
                            break;
                        }
                        salida.add(TipoComando.describirJugador);
                        salida.add(comando.get(2));
                        break;

                    case "avatar":
                        if(argc < 3){
                            Output.errorComando("Opción del comando -describir- incorrecta.");
                            salida.add(null);
                            break;
                        }
                        salida.add(TipoComando.describirAvatar);
                        salida.add(comando.get(2));
                        break;

                    default:
                        salida.add(TipoComando.describirCasilla);
                        if(argc < 3){
                            salida.add(comando.get(1));
                        } else {
                            salida.add(comando.get(1) + " " + comando.get(2));
                        }
                        break;
                }
                break;

            case "comprar":
                if(argc < 3){
                    Output.errorComando("Opción del comando -comprar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    default:
                        salida.add(TipoComando.comprarPropiedad);
                        salida.add(comando.get(1));
                        break;
                }
                break;

            case "ver":
                if(argc < 3){
                    Output.errorComando("Opción del comando -ver- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
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

    private void interpretarComando(ArrayList<Object> comando){

        if((TipoComando)comando.get(0) == TipoComando.crearJugador) {
            String aux = "";
            char[] argc = ((String) comando.get(1)).toCharArray();
            int tamArg = ((String) comando.get(1)).length();

            ArrayList<String> argumentoSeparados = new ArrayList<>();

            for (int i = 0; i < tamArg; i++) {

                if (argc[i] == ' ') {
                    argumentoSeparados.add(aux);
                    aux = "";
                } else {
                    aux += argc[i];
                }

            }
            argumentoSeparados.add(aux);

            if(argumentoSeparados.size() < 2){

                Output.errorComando("Introduzca el avatar después del nombre en la opción «crear»");
                return;
            }

            TipoAvatar avatar = TipoAvatar.toAvatar((String) argumentoSeparados.get(1));

            if(avatar == null){

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

            Jugador jugador = new Jugador((String) argumentoSeparados.get(0), juego.getTablero(),
                    avatar, juego.getTablero().getCasillas().get(0).get(0));

            juego.addJugador(jugador);


        }
    }

}


