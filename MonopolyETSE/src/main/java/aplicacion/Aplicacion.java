package aplicacion;

import aplicacion.salidaPantalla.Output;
import monopoly.Juego;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {

    private Juego juego;

    public Aplicacion(){

        juego = new Juego();

    }

    public void introducirComando(Jugador jugador){

        Scanner entrada = new Scanner(System.in);
        String linea;

        Output.imprimirCabeceraJugador(jugador);
        Output.imprimirEntradaComando();

        //linea = entrada.nextLine();

        ArrayList<Object> comando = toComando("listar jugadores");

        if(comando.get(0) != null) {
            System.out.println(((TipoComando) comando.get(0)).getComando());
            if(comando.size() == 2)
                System.out.println((String) comando.get(1));
        }

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
                if(argc < 2){
                    //todo output errores
                    System.err.println("Opción del comando -crear- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "jugador":
                        salida.add(TipoComando.crearJugador);
                        break;

                    default:
                        System.err.println("Opción del comando -crear- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "jugador":
                salida.add(TipoComando.turno);
                break;

            case "listar":
                if(argc < 2){
                    System.err.println("Opción del comando -listar- incorrecta");
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
                        System.err.println("Opción del comando -listar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "lanzar":
                if(argc < 2){
                    System.err.println("Opción del comando -lanzar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "dados":
                        salida.add(TipoComando.lanzarDados);
                        break;

                    default:
                        System.err.println("Opción del comando -lanzar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "acabar":
                if(argc < 2){
                    System.err.println("Opción del comando -acabar- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "turno":
                        salida.add(TipoComando.finalizarTurno);
                        break;

                    default:
                        System.err.println("Opción del comando -acabar- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "salir":
                if(argc < 2){
                    System.err.println("Opción del comando -salir- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "carcel":
                        salida.add(TipoComando.salirCarcel);
                        break;

                    default:
                        System.err.println("Opción del comando -salir- incorrecta.");
                        salida.add(null);
                        break;
                }
                break;

            case "describir":
                if(argc < 2){
                    System.err.println("Opción del comando -describir- incorrecta.");
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
                            System.err.println("Opción del comando -describir- incorrecta.");
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
                    System.err.println("Opción del comando -comprar- incorrecta.");
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
                    System.err.println("Opción del comando -ver- incorrecta.");
                    salida.add(null);
                    break;
                }
                switch(comando.get(1)){
                    case "tablero":
                        salida.add(TipoComando.verTablero);
                        break;

                    default:
                        System.err.println("Opción del comando -ver- incorrecta.");
                        break;
                }
                break;

            default:
                System.err.println("Comando incorrecto.");
                salida.add(null);
                break;

        }

        return salida;
    }

}


