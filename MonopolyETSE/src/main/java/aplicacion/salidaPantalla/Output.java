package aplicacion.salidaPantalla;

import monopoly.Constantes;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Casilla;
import monopoly.tablero.Edificio;
import monopoly.tablero.TipoEdificio;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Output {

    private static ArrayList<ArrayList<Object>> buffer;
    private static boolean impresionTablero;

    public static ArrayList<ArrayList<Object>> getBuffer() {
        return buffer;
    }

    public static void setBuffer(ArrayList<ArrayList<Object>> buffer) {
        if(buffer == null){
            System.out.println("Buffer referencia a null");
            return;
        }
        Output.buffer = buffer;
    }

    public static void addBuffer(String impresion, Integer ancho, Integer alto){

        ArrayList<Object> aux = new ArrayList<>();

        if(buffer == null){
            System.out.println("Buffer referencia a null en addBuffer.");
            return;
        }

        aux.add(impresion);
        aux.add(ancho);
        aux.add(alto);

        buffer.add(aux);
    }

    public static void vaciarBuffer(){
        while(!buffer.isEmpty()){
            buffer.remove(0);
        }
    }

    public static boolean isImpresionTablero() {
        return impresionTablero;
    }

    private static void imprimirCabecera(ArrayList<String> mensajes, int ancho, int alto) {

        if (mensajes == null) {
            System.err.println("Mensajes no inicializado.");
            return;
        }

        if (ancho < 0)
            ancho = 0;

        if (alto < 0)
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
        for (int i = 0; i < numMensajes; i++) {
            int aux = mensajes.get(i).length();

            tamMensaje.add(aux);
            sumLongitudMensajes += aux;

            int posicion = 0;
            for (int j = i; j >= 0; j--) {

                posicion += 2 * ancho + tamMensaje.get(j) + 1;

            }

            posiciones.add(posicion);
        }

        //La longitud de la cabecera se calcula de la siguiente forma. Los mensajes van a estar separados entre barras.
        //Entre la barra y el mensaje hay una separación por espacios de "ancho" de ahí 2*ancho*numMensajes. Habrá tanta
        //barras como mensajes haya, más una al final. Por último hay que tener en cuenta la longitud de los propios
        //mensajes.
        longitudCabecera = 2 * ancho * (numMensajes) + numMensajes + 1 + sumLongitudMensajes;

        //Se introduce la primera línea de la cabecera, cuya esquina superior izquierda tiene el caracter "╭".
        impresion.append("╭");

        //Se introduce el resto de la primera línea, se le resta uno a la longitud de la cabecera para insertar las
        //esquinas.

        int contador = 0;

        for (int i = 1; i < longitudCabecera - 1; i++) {

            //En caso de que i sea igual a la posición de cuando se cambia de mensaje se imprime otro caracter y se
            //incremente la variable contador que indica el bloque actual.
            if (i == posiciones.get(contador)) {
                contador++;
                impresion.append("┬");
            } else {
                impresion.append("─");
            }

        }

        impresion.append("╮\n");

        //Se imprime la línea en blanco tantas veces como se haya indicado en alto.

        for (int i = 0; i < alto; i++) {

            contador = 0;

            impresion.append("│");

            //Se empieza en 1 porque empieza el primer caracter y el último se escriben fuera del for
            for (int j = 1; j < longitudCabecera - 1; j++) {

                if (j == posiciones.get(contador)) {
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
        for (int i = 1; i < longitudCabecera; i += 2 * ancho + 1) {

            //Impresión del ANCHO inicial
            for (int j = 0; j < ancho; j++)
                impresion.append(" ");

            //Impresión del MENSAJE
            impresion.append(mensajes.get(contador));
            i += tamMensaje.get(contador);
            contador++;

            //Impresión del ANCHO final
            for (int j = 0; j < ancho; j++)
                impresion.append(" ");

            //Impresión del último caracter
            impresion.append("│");
        }

        impresion.append("\n");
        //Impresión de líneas en blanco para el ALTO
        for (int i = 0; i < alto; i++) {

            contador = 0;

            impresion.append("│");

            //Se empieza en 1 porque empieza el primer caracter y el último se escriben fuera del for
            for (int j = 1; j < longitudCabecera - 1; j++) {

                if (j == posiciones.get(contador)) {
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

        for (int i = 1; i < longitudCabecera - 1; i++) {

            //En caso de que i sea igual a la posición de cuando se cambia de mensaje se imprime otro caracter y se
            //incremente la variable contador que indica el bloque actual.
            if (i == posiciones.get(contador)) {
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

        aux.append(((Integer) jugador.getFortuna()).toString() + "K €");

        informacion.add(aux.toString());

        informacion.add(jugador.getAvatar().getPosicion().getNombre());
        informacion.add("");

        imprimirCabecera(informacion, 1, 0);
    }

    public static void imprimirEntradaComando() {

        System.out.print("\uD83D\uDC49 Acción: ");

    }


    public static void imprimirRecuadro(ArrayList<String> mensaje, String tipo, TipoColor color, int ancho, int alto) {
        StringBuilder impresion = new StringBuilder();

        if (mensaje == null) {
            System.err.println("Mensajes no inicializados.");
            return;
        }

        int lineas = mensaje.size();

        int max = 0;

        int caracteresContar = 0;

        String opcion;

        if (ancho < 0)
            ancho = 0;
        if (alto < 0)
            alto = 0;

        for (int i = 0; i < lineas; i++) {
            int aux = mensaje.get(i).length();
            if (max < aux)
                max = mensaje.get(i).length();
        }

        switch (tipo) {
            case "sugerencia":
                opcion = "(S) SUGERENCIA: ";
                break;

            case "error":
                opcion = "(!) ERROR: ";
                break;

            case "respuesta":
                opcion = "(R) ";
                break;

            case "mensaje":
                opcion = "";
                break;
            default:
                opcion = "(*) " + tipo;
                break;
        }

        caracteresContar += opcion.length();

        impresion.append(color.getLetra());
        impresion.append("╔");

        for (int i = 0; i < max + 2 * ancho + caracteresContar; i++) {
            impresion.append("═");
        }

        impresion.append("╗").append(TipoColor.resetAnsi.getLetra()).append("\n");

        for (int i = 0; i < alto; i++) {
            impresion.append(color.getLetra());
            impresion.append("║");
            for (int j = 0; j < max + 2 * ancho + caracteresContar; j++) {
                impresion.append(" ");
            }
            impresion.append("║").append(TipoColor.resetAnsi.getLetra()).append("\n");
        }

        for (int i = 0; i < lineas; i++) {

            impresion.append(color.getLetra());
            impresion.append("║");

            for (int j = 0; j < ancho; j++)
                impresion.append(" ");

            if (i == 0) {

                //impresion.append(color.getLetra());
                //impresion.append(TipoColor.Negrita.getLetra());
                impresion.append(opcion);
                impresion.append(mensaje.get(i));
                //impresion.append(TipoColor.resetAnsi.getLetra());
                //impresion.append(color.getLetra());

                for (int j = 0; j < ancho + max - mensaje.get(i).length(); j++) {
                    impresion.append(" ");
                }

            } else {

                impresion.append(mensaje.get(i));

                int longMensaje = mensaje.get(i).length();

                for (int j = 0; j < max - longMensaje + caracteresContar + ancho; j++) {
                    impresion.append(" ");
                }
            }

            impresion.append("║").append(TipoColor.resetAnsi.getLetra()).append("\n");
        }

        for (int i = 0; i < alto; i++) {
            impresion.append(color.getLetra());
            impresion.append("║");
            for (int j = 0; j < max + 2 * ancho + caracteresContar; j++) {
                impresion.append(" ");
            }
            impresion.append("║").append(TipoColor.resetAnsi.getLetra()).append("\n");
        }
        impresion.append(color.getLetra());
        impresion.append("╚");

        for (int i = 0; i < max + 2 * ancho + caracteresContar; i++) {
            impresion.append("═");
        }

        impresion.append("╝").append(TipoColor.resetAnsi.getLetra()).append("\n");

        Integer anchoTotal = max + 2 * ancho + caracteresContar + 2 + 10;
        Integer altoTotal = lineas + 2*alto + 2;

        if(TableroASCII.anchoDisponible < anchoTotal || TableroASCII.altoDisponible < altoTotal)
            impresionTablero = false;
        else
            impresionTablero = true;

        addBuffer(impresion.toString(), anchoTotal, altoTotal);
    }

    //Función que devuelve un ArrayList con los datos del jugador pasados a String --> util para las funciones de
    //imprimir recuadro
    public static ArrayList<String> JugadortoArrayString(Jugador jugador) {
        if (jugador == null) {
            System.err.println("Jugador referencia a null.");
            return null;
        }

        //En datos se almacenarán los datos que se devuelven.
        ArrayList<String> datos = new ArrayList<>();

        //Se añade el nombre, el identificador del avatar y la fortuna del jugador.
        datos.add("(*) Jugador: " + jugador.getNombre());
        datos.add("        -> Avatar: " + ((Character) jugador.getAvatar().getIdentificador()).toString());
        datos.add("        -> Fortuna: " + ((Integer) jugador.getFortuna()).toString() + "K €");

        //Numero de propiedades del jugador
        int numPropiedades = jugador.getPropiedades().size();

        //En la variable auxiliar se añadirá el String a añadir de las propiedades
        StringBuilder prop = new StringBuilder();
        //Se calcula el número de propiedades y de prop hipotecadas para que si son 0 añadir un formato especial.
        int numProp = 0;

        //Lo mismo que la anterior pero con las propiedades hipotecadas
        StringBuilder propHipotecadas = new StringBuilder();
        int numHip = 0;
        ArrayList<Casilla> casillas;
        prop.append("        -> Propiedades: {");
        propHipotecadas.append("        -> Propiedades hipotecadas: {");

        for (int i = 0; i < numPropiedades; i++) {

            casillas = jugador.getPropiedades();

            //En caso de que la casilla esté hipotecada se añade a su StringBuilder correspondiente
            if (casillas.get(i).isHipotecada()) {

                //se añade el nombre de la casilla
                propHipotecadas.append(casillas.get(i).getNombre());

                //En caso de que sea la última propiedad del jugador no se añade la coma
                if (i != numPropiedades - 1)
                    propHipotecadas.append(", ");

                numHip++;

            } else {
                //se añade el nombre de la casilla
                prop.append(casillas.get(i).getNombre());

                //En caso de que sea la última propiedad del jugador no se añade la coma
                if (i != numPropiedades - 1)
                    prop.append(", ");

                numProp++;

            }

        }


        if (numProp == 0) {
            prop.append("Sin propiedades");
        }
        if (numHip == 0) {
            propHipotecadas.append("Sin propiedades hipotecadas");
        }

        prop.append("}");
        propHipotecadas.append("}");

        datos.add(prop.toString());
        datos.add(propHipotecadas.toString());

        return datos;
    }

    public static ArrayList<String> AvatartoArrayString(Avatar avatar) {

        ArrayList<String> informacion = new ArrayList<>();

        informacion.add("(*) Avatar ID: " + avatar.getIdentificador());
        informacion.add("        -> Tipo: " + avatar.getTipo().getNombre());
        informacion.add("        -> Casilla: " + avatar.getPosicion().getNombre());
        informacion.add("        -> Jugador: " + avatar.getJugador().getNombre());

        return informacion;
    }

    public static ArrayList<String> CasillatoArrayString(Casilla casilla) {

        ArrayList<String> informacion = new ArrayList<>();
        Edificio aux;

        int valorCasilla;

        informacion.add("(*) Casilla: " + casilla.getNombre());

        //todo anda pásalo a un switch coñe

        if (casilla.getGrupo().getTipo() != TipoGrupo.carcel && casilla.getGrupo().getTipo() != TipoGrupo.parking &&
                casilla.getGrupo().getTipo() != TipoGrupo.salida && casilla.getGrupo().getTipo() != TipoGrupo.irCarcel)
            informacion.add("        -> Tipo: " + casilla.getGrupo().getTipo().getTipoCasilla());


        if (casilla.getGrupo().getTipo() == TipoGrupo.servicios || casilla.getGrupo().getTipo() ==
                TipoGrupo.transporte)
            valorCasilla = casilla.getGrupo().getPrecio();
        else
            valorCasilla = (int) (casilla.getGrupo().getPrecio() / (double) casilla.getGrupo().getCasillas().size());

        // Debe diferenciarse entre aquellas casillas que tengan un precio asociado y aquellas que no (como las
        // de suerte o de comunidad)
        if (valorCasilla >= 0) {

            int alquiler = casilla.getAlquiler();

            if (casilla.getGrupo().getTipo() == TipoGrupo.impuesto1 ||
                    casilla.getGrupo().getTipo() == TipoGrupo.impuesto2) {

                informacion.add("");
                informacion.add("        -> A pagar: " + casilla.getGrupo().getPrecio());


            } else if (casilla.getGrupo().getTipo() == TipoGrupo.parking) {

                informacion.add("        -> Bote: " + casilla.getGrupo().getPrecio());

                StringBuilder jugadoresContenidos = new StringBuilder("        -> Jugadores: {");

                Set<String> avatares = casilla.getAvataresContenidos().keySet();

                Avatar avatarAux;

                boolean flag = false;

                for (String avatar : avatares) {

                    avatarAux = casilla.getAvataresContenidos().get(avatar);

                    jugadoresContenidos.append(avatarAux.getJugador().getNombre());

                    jugadoresContenidos.append(", ");

                }

                int tam = jugadoresContenidos.toString().length();

                jugadoresContenidos.replace(tam, tam, "}");

                informacion.add(jugadoresContenidos.toString());

            } else if(casilla.getGrupo().getTipo() == TipoGrupo.carcel){

                informacion.add("        -> Salir: " + casilla.getGrupo().getPrecio());
                StringBuilder jugadoresEncarcelados = new StringBuilder("        -> Jugadores encarcelados: {");

                Set<String> avatares = casilla.getAvataresContenidos().keySet();

                boolean flag = false;

                for(String avatar: avatares) {

                    if(flag) {
                        jugadoresEncarcelados.append(" , {");
                    }
                    jugadoresEncarcelados.append(casilla.getAvataresContenidos().get(avatar).getJugador().getNombre());
                    jugadoresEncarcelados.append(", ");
                    jugadoresEncarcelados.append(casilla.getAvataresContenidos().get(avatar).getTurnosEnCarcel());
                    jugadoresEncarcelados.append("}");
                    flag = true;

                }


                informacion.add(jugadoresEncarcelados.toString());

            } else if (casilla.getGrupo().getTipo() == TipoGrupo.salida) {
                informacion.add("        -> Dinero a recibir: " + casilla.getGrupo().getPrecio() + "K €");
            } else {
                informacion.add("        -> Grupo: " + casilla.getGrupo().getTipo());
                informacion.add("        -> Propietario: " + casilla.getPropietario().getNombre());
                informacion.add("");
                informacion.add("        -> Valor:                            " + valorCasilla + "K €");
                informacion.add("        -> Alquiler:                         " + alquiler + "K €");

                if( casilla.getGrupo().getTipo().getTipoCasilla().equals(TipoGrupo.azul.getTipoCasilla() )) {
                    informacion.add("");
                    aux = new Edificio(TipoEdificio.hotel, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor hotel:                      " + aux.getPrecioCompra() + "K €");

                    aux = new Edificio(TipoEdificio.casa, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor casa:                       " + aux.getPrecioCompra() + "K €");

                    aux = new Edificio(TipoEdificio.piscina, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor piscina:                    " + aux.getPrecioCompra() + "K €");

                    aux = new Edificio(TipoEdificio.pistaDeporte, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor pista de deporte:           " + aux.getPrecioCompra() + "K €");

                    informacion.add("");
                    informacion.add("        -> Alquiler con una casa:            " + Constantes.ALQ_UNACASA * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con dos casas:           " + Constantes.ALQ_DOSCASA * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con tres casas:          " + Constantes.ALQ_TRESCASA * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con cuatro casas:        " + Constantes.ALQ_CUATROCASA * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con un hotel:            " + Constantes.ALQ_HOTEL * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con un piscina:          " + Constantes.ALQ_PISCINA * alquiler
                            + "K €");
                    informacion.add("        -> Alquiler con un pista de deporte: " + Constantes.ALQ_PISTADEPORTE * alquiler
                            + "K €");
                }
            }

        }

        return informacion;

    }

    public static void errorComando(String error) {
        ArrayList<String> errores = new ArrayList<>();
        errores.add(error);
        errorComando(errores);
    }

    public static void errorComando(String... errores) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String err : errores) {
            informacion.add(err);
        }

        errorComando(informacion);

    }

    public static void errorComando(ArrayList<String> error) {
        imprimirRecuadro(error, "error", TipoColor.rojoANSI, 3, 1);
    }

    //todo sacar los String individuales
    public static void sugerencia(String sugerencia) {
        ArrayList<String> sugerencias = new ArrayList<>();
        sugerencias.add(sugerencia);
        sugerencia(sugerencias);
    }

    public static void sugerencia(String... sugerencias) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String sug : sugerencias) {
            informacion.add(sug);
        }

        sugerencia(informacion);

    }

    public static void sugerencia(ArrayList<String> sugerencia) {
        imprimirRecuadro(sugerencia, "sugerencia", TipoColor.verdeANSI, 2, 0);
    }

    public static void respuesta(String respuesta) {
        ArrayList<String> respuestas = new ArrayList<>();
        respuestas.add(respuesta);
        respuesta(respuestas);
    }

    public static void respuesta(String... respuestas) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String resp : respuestas) {
            informacion.add(resp);
        }

        respuesta(informacion);

    }

    public static void respuesta(ArrayList<String> respuestas) {
        imprimirRecuadro(respuestas, "respuesta", TipoColor.cianANSI, 2, 0);
    }

    public static void mensaje(String... mensajes) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String resp : mensajes) {
            informacion.add(resp);
        }

        mensaje(informacion);

    }

    public static void mensaje(ArrayList<String> mensajes) {
        imprimirRecuadro(mensajes, "mensaje", TipoColor.amarilloANSI, 2, 0);
    }

    public static void imprimirAyuda(){
        ArrayList<String> ayuda = new ArrayList<>();
        ayuda.add("Información sobre comandos.");
        ayuda.add("");
        ayuda.add(" -> crear jugador <nombre> <tipo_Avatar>");
        ayuda.add("      (*) Crea un jugador con el nombre introducido y su tipo de avatar.");
        ayuda.add("      (*) Tipos de avatares disponibles:");
        ayuda.add("               - Coche");
        ayuda.add("               - Esfinge");
        ayuda.add("               - Sombrero");
        ayuda.add("               - Pelota");
        ayuda.add("");
        ayuda.add(" -> jugador");
        ayuda.add("      (*) Informa del jugador que tiene el turno.");
        imprimirRecuadro(ayuda,"AYUDA: ", TipoColor.violetaANSI, 2, 1);
    }
}
