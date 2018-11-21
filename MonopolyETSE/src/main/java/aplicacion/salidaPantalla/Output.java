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

    //Buffer en el que se almacerán ArrayList de object, los cuales almacenan la tupla String - alto - ancho
    //El buffer se vacía cuando se imprime por pantalla
    private static ArrayList<ArrayList<Object>> buffer;

    //Booleano para indicar si el buffer se imprimirá en el tablero.
    private static boolean impresionTablero;

    /*Getters*/
    public static ArrayList<ArrayList<Object>> getBuffer() {
        return buffer;
    }

    public static boolean isImpresionTablero() {
        return impresionTablero;
    }

    public static void setBuffer(ArrayList<ArrayList<Object>> buffer) {
        if(buffer == null){
            System.out.println("Buffer referencia a null");
            return;
        }
        Output.buffer = buffer;
    }

    /**
     * Función para añadir un mensaje al buffer
     *
     * @param impresion mensaje a imprimir
     * @param ancho ancho ocupado por el mensaje
     * @param alto alto ocupado por el mensaje
     */
    public static void addBuffer(String impresion, Integer ancho, Integer alto){

        //Variable auxiliar para enviar al buffer la información

        ArrayList<Object> aux = new ArrayList<>();

        if(getBuffer() == null){
            System.out.println("Buffer referencia a null en addBuffer.");
            return;
        }

        aux.add(impresion);
        aux.add(ancho);
        aux.add(alto);

        getBuffer().add(aux);
    }

    /**
     * Función para eliminar la información del buffer
     */
    public static void vaciarBuffer(){
        while(!buffer.isEmpty()){
            buffer.remove(0);
        }
    }

    /**
     * Función para crear e imprimir la cabecera del usuario. La cabecera se creará en función del ArrayList de los
     * mensajes, cada elemento del ArrayList se imprime en una columna distinta
     * @param mensajes ArrayList con los mensajes a imprimir
     * @param ancho ancho entre el recuadro y el mensaje
     * @param alto alto entre el recuadro y el mensaje
     */
    private static void imprimirCabecera(ArrayList<String> mensajes, int ancho, int alto) {

        if (mensajes == null) {
            System.err.println("Mensajes no inicializado.");
            return;
        }

        if (ancho < 0)
            ancho = 0;

        if (alto < 0)
            alto = 0;

        //Variable para guardar el recuadro
        StringBuilder impresion = new StringBuilder();

        //Se calcula el número de mensajes que hay
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


    /**
     * Función que llama a imprimirCabecera para imprimir la cabecera del jugador
     * @param jugador jugador del que se imprimirá la cabecera
     */
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

    /**
     * Función para imprimir la entrada para introucir el comando.
     */
    public static void imprimirEntradaComando() {

        System.out.print("\uD83D\uDC49 Acción: ");

    }

    /**
     * Función para crear y guardar en el buffer un recuadro con la información pasada en el ArrayList de mensajes.
     * La cabecera se creará en función del ArrayList de los mensajes, cada elemento del ArrayList se imprime en una
     * línea distinta
     * @param mensaje ArrayList en el que se almacenan los mensajes por líneas a imprimir
     * @param tipo se le envía el tipo de recuadro que se quiere imprimir, en caso de que el tipo no exista se utiliza
     *             el introducido por el usuario
     * @param color color con el que se desea que se imprima el recuadro
     * @param ancho ancho entre el recuadro y el mensaje
     * @param alto alto entre el recuadro y el mensaje
     */
    public static void imprimirRecuadro(ArrayList<String> mensaje, String tipo, TipoColor color, int ancho, int alto) {

        //Variable en la que se guarda el mensaje a mandar al buffer
        StringBuilder impresion = new StringBuilder();

        if (mensaje == null) {
            System.err.println("Mensajes no inicializados.");
            return;
        }

        //Se calcula el número de líneas a imprimir
        int lineas = mensaje.size();

        int max = 0;

        int caracteresContar = 0;

        //String en el que se guardará lo que se imprimirá en función de la opción.
        String opcion;

        if (ancho < 0)
            ancho = 0;

        if (alto < 0)
            alto = 0;

        //Se calcula la longitud de los mensajes del ArrayList y se calcula el máximo
        for (String aMensaje : mensaje) {
            int aux = aMensaje.length();
            if (max < aux)
                max = aMensaje.length();
        }

        //Se escoge el mensaje a imprimir en función del tipo
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

        //En caracteresContar se añade los caracteres que se imprimen a mayores que no forman parte del ArrayList
        //mensaje, o sea, el tamaño de la opcion
        caracteresContar += opcion.length();

        //Se añade el color que ha introducido el usuario
        impresion.append(color.getLetra());
        impresion.append("╔");

        //El ancho del recuadro será el máximo calculado sumado al doble del ancho más los caracteres que se imprimen
        //a mayores
        for (int i = 0; i < max + 2 * ancho + caracteresContar; i++) {
            impresion.append("═");
        }

        impresion.append("╗").append(TipoColor.resetAnsi.getLetra()).append("\n");

        //Se imprimen tantas líneas con caracteres en blanco como alto introducido
        for (int i = 0; i < alto; i++) {
            impresion.append(color.getLetra());
            impresion.append("║");
            for (int j = 0; j < max + 2 * ancho + caracteresContar; j++) {
                impresion.append(" ");
            }
            impresion.append("║").append(TipoColor.resetAnsi.getLetra()).append("\n");
        }

        //Se imprimen los mensajes introducidos en el ArrayList
        for (int i = 0; i < lineas; i++) {

            impresion.append(color.getLetra());
            impresion.append("║");

            for (int j = 0; j < ancho; j++)
                impresion.append(" ");

            //En caso de que sea el primer mensaje que se imprime, también se imprime la opción seleccionada
            if (i == 0) {

                impresion.append(opcion);
                impresion.append(mensaje.get(i));

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

        //El ancho total es lo que se imprime más 2 de los bordes y más 10 del color
        Integer anchoTotal = max + 2 * ancho + caracteresContar + 2 + 10;
        Integer altoTotal = lineas + 2*alto + 2;

        //Se comprueba si el mensaje entraría dentro del tablero, en caso de que sí se pone impresionTablero a true
        if(TableroASCII.anchoDisponible < anchoTotal || TableroASCII.altoDisponible < altoTotal)
            impresionTablero = false;
        else
            impresionTablero = true;

        addBuffer(impresion.toString(), anchoTotal, altoTotal);

    }

    //Función que devuelve un ArrayList con los datos del jugador pasados a String --> util para las funciones de
    //imprimir recuadro

    /**
     * Función que devuelve un ArrayList de String para mandar a imprimirRecuadro, con los datos del jugador
     * @param jugador jugador del que se guarda la información
     */
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

    /**
     * Función que devuelve un ArrayList de String para mandar a imprimirRecuadro, con los datos del avatar
     * @param avatar avatar del que se guarda la información
     */
    public static ArrayList<String> AvatartoArrayString(Avatar avatar) {

        ArrayList<String> informacion = new ArrayList<>();

        informacion.add("(*) Avatar ID: " + avatar.getIdentificador());
        informacion.add("        -> Tipo: " + avatar.getTipo().getNombre());
        informacion.add("        -> Casilla: " + avatar.getPosicion().getNombre());
        informacion.add("        -> Jugador: " + avatar.getJugador().getNombre());

        return informacion;
    }

    /**
     * Función que devuelve un ArrayList de String para mandar a imprimirRecuadro, con los datos de la casilla
     * @param casilla casilla del que se guarda la información
     */
    public static ArrayList<String> CasillatoArrayString(Casilla casilla) {

        ArrayList<String> informacion = new ArrayList<>();
        Integer aux;

        int valorCasilla;

        informacion.add("(*) Casilla: " + casilla.getNombre());


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

                informacion.add("        -> Bote: " + casilla.getGrupo().getCasillas().get(0).getAlquiler());

                StringBuilder jugadoresContenidos = new StringBuilder("        -> Jugadores: {");

                Set<Character> avatares = casilla.getAvataresContenidos().keySet();

                Avatar avatarAux;

                boolean flag = false;

                for (Character avatar : avatares) {

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

                Set<Character> avatares = casilla.getAvataresContenidos().keySet();

                boolean flag = false;

                for(Character avatar: avatares) {

                    if(casilla.getAvataresContenidos().get(avatar).isEncarcelado()) {
                        if (flag) {
                            jugadoresEncarcelados.append(" , {");
                        }
                        jugadoresEncarcelados.append(casilla.getAvataresContenidos().get(avatar).getJugador().getNombre());
                        jugadoresEncarcelados.append(", ");
                        jugadoresEncarcelados.append(casilla.getAvataresContenidos().get(avatar).getTurnosEnCarcel());
                        jugadoresEncarcelados.append("}");
                        flag = true;
                    }

                }

                if(!flag){
                    jugadoresEncarcelados.append("no hay jugadores encarcelados :-)}");
                }


                informacion.add(jugadoresEncarcelados.toString());

            } else if (casilla.getGrupo().getTipo() == TipoGrupo.salida) {
                informacion.add("        -> Dinero a recibir: " + casilla.getGrupo().getPrecio() + "K €");
            } else {
                informacion.add("        -> Grupo: " + casilla.getGrupo().getTipo());
                informacion.add("        -> Propietario: " + casilla.getPropietario().getNombre());
                informacion.add("");
                informacion.add("        -> Valor:                            " + valorCasilla + "K €");

                if(casilla.getGrupo().getTipo() == TipoGrupo.transporte){
                    informacion.add("        -> Alquiler con 1 transporte:        " + (int)valorCasilla*0.25 + "K €");
                    informacion.add("        -> Alquiler con 2 transportes:       " + (int)valorCasilla*0.5 + "K €");
                    informacion.add("        -> Alquiler con 3 transportes:       " + (int)valorCasilla*0.75 + "K €");
                    informacion.add("        -> Alquiler con 4 transportes:       " + (int)valorCasilla + "K €");
                } else {
                    informacion.add("        -> Alquiler:                         " + alquiler + "K €");
                }

                if( casilla.getGrupo().getTipo().getTipoCasilla().equals(TipoGrupo.azul.getTipoCasilla() )) {
                    informacion.add("");
                    aux = Edificio.calcularPrecioCompra(TipoEdificio.hotel, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor hotel:                      " + aux + "K €");

                    aux = Edificio.calcularPrecioCompra(TipoEdificio.casa, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor casa:                       " + aux + "K €");

                    aux = Edificio.calcularPrecioCompra(TipoEdificio.piscina, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor piscina:                    " + aux + "K €");

                    aux = Edificio.calcularPrecioCompra(TipoEdificio.pistaDeporte, casilla.getGrupo().getTipo());
                    informacion.add("        -> Valor pista de deporte:           " + aux + "K €");

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


    /**
     * Función a la que se pasa un atributo multivalorado de String y guarda en el buffer un recuadro de tipo error
     * @param errores mensajes a mandar al recuadro
     */
    public static void errorComando(String... errores) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String err : errores) {
            informacion.add(err);
        }

        errorComando(informacion);

    }

    /**
     * Función a la que se pasa un ArrayList de String y guarda en el buffer un recuadro de tipo error
     * @param error mensajes a mandar al recuadro
     */
    public static void errorComando(ArrayList<String> error) {
        imprimirRecuadro(error, "error", TipoColor.rojoANSI, 3, 1);
    }

    /**
     * Función a la que se pasa un atributo multivalorado de String y guarda en el buffer un recuadro de tipo sugerencia
     * @param sugerencias mensajes a mandar al recuadro
     */
    public static void sugerencia(String... sugerencias) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String sug : sugerencias) {
            informacion.add(sug);
        }

        sugerencia(informacion);

    }

    /**
     * Función a la que se pasa un ArrayList de String y guarda en el buffer un recuadro de tipo sugerencia
     * @param sugerencia mensajes a mandar al recuadro
     */
    public static void sugerencia(ArrayList<String> sugerencia) {
        imprimirRecuadro(sugerencia, "sugerencia", TipoColor.verdeANSI, 2, 0);
    }

    /**
     * Función a la que se pasa un atributo multivalorado de String y guarda en el buffer un recuadro de tipo respuesta
     * @param respuestas mensajes a mandar al recuadro
     */
    public static void respuesta(String... respuestas) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String resp : respuestas) {
            informacion.add(resp);
        }

        respuesta(informacion);

    }

    /**
     * Función a la que se pasa un ArrayList de String y guarda en el buffer un recuadro de tipo respuesta
     * @param respuestas mensajes a mandar al recuadro
     */
    public static void respuesta(ArrayList<String> respuestas) {
        imprimirRecuadro(respuestas, "respuesta", TipoColor.cianANSI, 2, 0);
    }

    /**
     * Función a la que se pasa un atributo multivalorado de String y guarda en el buffer un recuadro de tipo mensaje
     * @param mensajes mensajes a mandar al recuadro
     */
    public static void mensaje(String... mensajes) {

        ArrayList<String> informacion = new ArrayList<>();

        for (String resp : mensajes) {
            informacion.add(resp);
        }

        mensaje(informacion);

    }

    /**
     * Función a la que se pasa un atributo multivalorado de String y guarda en el buffer un recuadro de tipo mensaje
     * @param mensajes mensajes a mandar al recuadro
     */
    public static void mensaje(ArrayList<String> mensajes) {
        imprimirRecuadro(mensajes, "mensaje", TipoColor.amarilloANSI, 2, 0);
    }

    /**
     * Función que manda al buffer la Ayuda
     */


    public static void ayuda(String... ayuda){
        ArrayList<String> informacion = new ArrayList<>();

        for (String resp : ayuda) {
            informacion.add(resp);
        }

        imprimirRecuadro(informacion,"mensaje", TipoColor.violetaANSI, 2, 1);
    }

    public static void imprimirAyuda(){
        ArrayList<String> ayuda = new ArrayList<>();

        Output.ayuda("",
        "                                _.--,-```-.    " ,
        "                               /    /      '.  " ,
        "                              /  ../         ; " ,
        "                              \\  ``\\  .``-    '" ,
        "                               \\ ___\\/    \\   :" ,
        "                                     \\    :   |" ,
        "                                     |    ;  . " ,
        "                                    ;   ;   :  " ,
        "                                   /   :   :   " ,
        "                                   `---'.  |   " ,
        "                                    `--..`;    " ,
        "                                   .--,_        " ,
        "                                  |    |`.     " ,
        "                                  `-- -`, ;    " ,
        "                                    '---`\"     " ,
        "                                              "

        ,"Información sobre comandos."
        ,""
        ," -> crear jugador <nombre> <tipo_Avatar>"
        ,"      (*) Crea un jugador con el nombre introducido y su tipo de avatar."
        ,"      (*) Tipos de avatares disponibles:"
        ,"               - Coche"
        ,"               - Esfinge"
        ,"               - Sombrero"
        ,"               - Pelota"
        ,""
        ," -> jugador"
        ,"      (*) Informa del jugador que tiene el turno."
        ,""
        ," -> iniciar"
        ,"      (*) Inicia el juego si hay dos o más jugadores."
        ,"      (!) No se pueden añadir más jugadores una vez iniciado el juego."
        ,""
        ," -> listar jugadores"
        ,"      (*) Imprime información sobre todos los jugadores del juego."
        ,""
        ," -> listar avatares"
        ,"      (*) Imprime información sobre todos los avatares del tablero."
        ,""
        ," -> listar enventa"
        ,"      (*) Imprime información sobre todas las casillas que están en venta."
        ,""
        ," -> lanzar dados"
        ,"      (*) Se lanzan los dos dados del juego y se avanza el número de casillas"
        ,"          correspondiente a la suma de los dos."
        ,""
        ," -> acabar turno"
        ,"      (*) Finaliza el turno actual y pasa al siguiente jugador."
        ,"      (!) No se puede pasar el turno sin haber tirado los dados."
        ,""
        ," -> salir carcel"
        ,"      (*) Se paga el importe correspondiente y se libera el avatar de la cárcel."
        ,"      (!) Asegúrate de estar encarcelado."
        ,""
        ," -> describir jugador <nombre>"
        ,"      (*) Se describe el jugador con el nombre introducido (si existe)."
        ,""
        ," -> describir avatar <id>"
        ,"      (*) Se describe el avatar con el ID introducido (si existe)."
        ,""
        ," -> describir <casilla>"
        ,"      (*) Se describe la casilla con el nombre introducido (si existe)."
        ,""
        ," -> comprar <casilla>"
        ,"      (*) Se compra la casilla indicada en el comando."
        ,"      (!) Solo puedes comprar una casilla si tu avatar está situado en ella."
        ,"      (!) Asegúrate de tener suficiente liquidez."
        ,""
        ," -> ver tablero"
        ,"      (*) Muestra al jugador el tablero.");


    }
}
