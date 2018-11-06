package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Output {

    private static void imprimirCabecera(ArrayList<String> mensajes, int ancho, int alto){

        if(mensajes == null){
            System.err.println("Mensajes no inicializado.");
            return;
        }

        if(ancho < 0)
            ancho = 0;

        if(alto < 0)
            alto = 0;

        StringBuilder impresion = new StringBuilder();

        int numMensajes = mensajes.size();

        //La suma de la longitud de los mensajes
        int sumLongitudMensajes = 0;

        //ArrayList donde se almacenarán el tamaño de cada mensaje por orden.
        ArrayList<Integer> tamMensaje = new ArrayList<>();

        //ArrayList donde se almacenarán las posiciones donde se cambia de bloque (de mensaje)
        ArrayList<Integer> posiciones = new ArrayList<>();

        int longitudCabecera;

        //Calculo de la suma de la longitud de los mensajes y el tamaño del bloque.
        for(int i = 0; i < numMensajes; i++) {
            int aux = mensajes.get(i).length();

            tamMensaje.add(aux);
            sumLongitudMensajes += aux;

            int posicion = 0;
            for(int j = i; j >= 0; j--){

                posicion += 2*ancho+tamMensaje.get(j)+1;

            }

            posiciones.add(posicion);
        }

        //La longitud de la cabecera se calcula de la siguiente forma. Los mensajes van a estar separados entre barras.
        //Entre la barra y el mensaje hay una separación por espacios de "ancho" de ahí 2*ancho*numMensajes. Habrá tanta
        //barras como mensajes haya, más una al final. Por último hay que tener en cuenta la longitud de los propios
        //mensajes.
        longitudCabecera = 2*ancho*(numMensajes)+numMensajes+1+sumLongitudMensajes;

        //Se introduce la primera línea de la cabecera, cuya esquina superior izquierda tiene el caracter "╭".
        impresion.append("╭");

        //Se introduce el resto de la primera línea, se le resta uno a la longitud de la cabecera para insertar las
        //esquinas.

        int contador = 0;

        for(int i = 1; i < longitudCabecera-1; i++){

            //En caso de que i sea igual a la posición de cuando se cambia de mensaje se imprime otro caracter y se
            //incremente la variable contador que indica el bloque actual.
            if(i == posiciones.get(contador)){
                contador++;
                impresion.append("┬");
            } else {
                impresion.append("─");
            }

        }

        impresion.append("╮\n");

        //Se imprime la línea en blanco tantas veces como se haya indicado en alto.

        for(int i = 0; i < alto; i++){

            contador = 0;

            impresion.append("│");

            //Se empieza en 1 porque empieza el primer caracter y el último se escriben fuera del for
            for(int j = 1; j < longitudCabecera-1; j++){

                if(j == posiciones.get(contador)){
                    contador++;
                    impresion.append("│");
                } else {
                    impresion.append(" ");
                }

            }

            impresion.append("│\n");

        }

        //LÍNEA DEL MENSAJE
        //Se añade el primer caracter de la línea, por eso el for empieza en i = 1
        impresion.append("│");

        //Contador inicializado a 0 --> cuenta el número de mensajes impresos
        contador = 0;
        //Por cada iteración del bucle se imprimen 2*ancho+1 caracteres a mayores del tamaño del mensaje
        for(int i = 1; i < longitudCabecera; i+=2*ancho+1){

            //Impresión del ANCHO inicial
            for(int j = 0; j < ancho; j++)
                impresion.append(" ");

            //Impresión del MENSAJE
            impresion.append(mensajes.get(contador));
            i += tamMensaje.get(contador);
            contador++;

            //Impresión del ANCHO final
            for(int j = 0; j < ancho; j++)
                impresion.append(" ");

            //Impresión del último caracter
            impresion.append("│");
        }

        impresion.append("\n");
        //Impresión de líneas en blanco para el ALTO
        for(int i = 0; i < alto; i++){

            contador = 0;

            impresion.append("│");

            //Se empieza en 1 porque empieza el primer caracter y el último se escriben fuera del for
            for(int j = 1; j < longitudCabecera-1; j++){

                if(j == posiciones.get(contador)){
                    contador++;
                    impresion.append("│");
                } else {
                    impresion.append(" ");
                }

            }

            impresion.append("│\n");

        }

        impresion.append("╰");

        //Se introduce el resto de la última línea, se le resta uno a la longitud de la cabecera para insertar las
        //esquinas.

        contador = 0;

        for(int i = 1; i < longitudCabecera-1; i++){

            //En caso de que i sea igual a la posición de cuando se cambia de mensaje se imprime otro caracter y se
            //incremente la variable contador que indica el bloque actual.
            if(i == posiciones.get(contador)){
                contador++;
                impresion.append("┴");
            } else {
                impresion.append("─");
            }

        }

        impresion.append("╯");
        System.out.println(impresion);
    }

    //Imprimir cabecera de un jugador

    public static void imprimirCabeceraJugador(Jugador jugador) {
        ArrayList<String> informacion = new ArrayList<>();
        StringBuilder aux = new StringBuilder();

        informacion.add("");

        informacion.add(jugador.getNombre());

        aux.append(((Integer)jugador.getFortuna()).toString()+"K €");

        informacion.add(aux.toString());

        informacion.add(jugador.getAvatar().getPosicion().getNombre());
        informacion.add("");

        imprimirCabecera(informacion, 1, 0);
    }

    public static void imprimirEntradaComando(){

        System.out.println("\t\uD83D\uDC49 Acción: ");

    }


    private static void imprimirRecuadro(ArrayList<String> mensaje, String tipo, TipoColor color, int ancho, int alto){
        StringBuilder impresion = new StringBuilder();

        int lineas = mensaje.size();

        int max = 0;

        int caracteresContar = 0;

        String opcion;

        if(ancho < 0)
            ancho = 0;
        if(alto < 0)
            alto = 0;

        if(mensaje == null){
            System.err.println("Mensajes no inicializados.");
            return;
        }

        for(int i = 0; i < lineas; i++){
            int aux = mensaje.get(i).length();
            if(max < aux)
                max = mensaje.get(i).length();
        }

        switch(tipo){
            case "sugerencia":
                opcion = "[S] SUGERENCIA: ";
                break;

            case "error":
                opcion = "[!] ERROR: ";
                break;

            case "respuesta":
                opcion = "[R] ";
                break;
            default:
                opcion = "[*] " + tipo;
                break;
        }

        caracteresContar += opcion.length();

        impresion.append(color.getLetra());
        impresion.append("\n╔");

        for(int i = 0; i < max+2*ancho+caracteresContar; i++){
            impresion.append("═");
        }

        impresion.append("╗\n");

        for(int i = 0; i < alto; i++){
            impresion.append("║");
            for(int j = 0; j < max+2*ancho+caracteresContar; j++){
                impresion.append(" ");
            }
            impresion.append("║\n");
        }

        for(int i = 0; i < lineas; i++) {

            impresion.append("║");

            for(int j = 0; j < ancho; j++)
                impresion.append(" ");

                if (i == 0) {

                    impresion.append(color.getLetra());
                    impresion.append(TipoColor.Negrita.getLetra());
                    impresion.append(opcion);
                    impresion.append(TipoColor.resetAnsi.getLetra());
                    impresion.append(color.getLetra());
                    impresion.append(mensaje.get(i));

                    for (int j = 0; j < ancho; j++) {
                        impresion.append(" ");
                    }

                } else {

                    impresion.append(mensaje.get(i));

                    int longMensaje = mensaje.get(i).length();

                    for (int j = 0; j < max - longMensaje + caracteresContar + ancho; j++) {
                        impresion.append(" ");
                    }
                }

            impresion.append("║\n");
        }

        for(int i = 0; i < alto; i++){
            impresion.append("║");
            for(int j = 0; j < max+2*ancho+caracteresContar; j++){
                impresion.append(" ");
            }
            impresion.append("║\n");
        }
        impresion.append("╚");

        for(int i = 0; i < max+2*ancho+caracteresContar; i++){
            impresion.append("═");
        }

        impresion.append("╝").append(TipoColor.resetAnsi.getLetra()).append("\n");

        System.out.println(impresion);
    }

    public static void errorComando(String error){
        ArrayList<String> errores = new ArrayList<>();
        errores.add(error);
        imprimirRecuadro(errores, "error", TipoColor.rojoANSI, 3, 1);
    }

    public static void errorComando(ArrayList<String> error){
        imprimirRecuadro(error, "error", TipoColor.rojoANSI, 3, 1);
    }

    public static void sugerencia(String sugerencia){
        ArrayList<String> sugerencias = new ArrayList<>();
        sugerencias.add(sugerencia);
        imprimirRecuadro(sugerencias, "sugerencia", TipoColor.verdeANSI, 3, 1);
    }

    public static void sugerencia(ArrayList<String> sugerencia){
        imprimirRecuadro(sugerencia, "sugerencia", TipoColor.verdeANSI, 3, 1);
    }

    public static void respuesta(String respuesta){
        ArrayList<String> respuestas = new ArrayList<>();
        respuestas.add(respuesta);
        imprimirRecuadro(respuestas, "respuesta", TipoColor.cianANSI, 3, 1);
    }

    public static void respuesta(ArrayList<String> respuestas){
        imprimirRecuadro(respuestas, "respuesta", TipoColor.verdeANSI, 3, 1);
    }
}
