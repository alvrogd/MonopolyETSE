package aplicacion.salidaPantalla;

import monopoly.jugadores.Avatar;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import aplicacion.salidaPantalla.Output;
import monopoly.tablero.TipoGrupo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class TableroASCII {

    /* Atributos */

    // Número de caracteres de una unidad
    private final static int caracteresPorUnidad = 3;

    // Tamaño de las casillas
    private final static int anchoCasilla = 7 * caracteresPorUnidad;
    private final static int altoCasilla = caracteresPorUnidad;

    // Número de casillas por cada fila (4 filas con 40 casillas en total, comezando desde la casilla de Salida)
    private final static int casillasPorFila = 10;
    // Número de casillas por cada lado del tablero
    private final static int casillasPorLado = 11;
    // Número de separadores por cada lado de casillas (1 izquierdo para cada casilla + el del extremo derecho)
    private final static int separadoresPorLado = casillasPorLado + 1;

    // Caracteres por línea (11 casillas, separadores y salto de línea)
    private final static int caracteresPorLinea = 11 * anchoCasilla + separadoresPorLado + 1;
    // Número de líneas
    private final static int numeroLineas = 11 * altoCasilla + separadoresPorLado;

    // Caracteres
    private final static char barraHorizontal = '─';
    private final static char barraVertical = '│';
    private final static char esquinaNO = '┘';
    private final static char esquinaNE = '└';
    private final static char esquinaNOE = '┴';
    private final static char esquinaNOS = '┤';
    private final static char esquinaNOES = '┼';
    private final static char esquinaNES = '├';
    private final static char esquinaOS = '┐';
    private final static char esquinaOES = '┬';
    private final static char esquinaES = '┌';

    // Tamaño disponible para la información a imprimir en el centro
    public final static int anchoDisponible = 9 * anchoCasilla + 8;
    public final static int altoDisponible = 9 * altoCasilla + 8;



    /* Métodos */

    /**
     * Coge un tablero y devuelve su representación visual como un String
     *
     * @param tablero tablero a pintar
     * @param info conjunto de información a representar en el interior del tablero; se considera si es distinto de
     *             null
     * @return String que representa el tablero a pintar
     */
    public static String pintaTablero(Tablero tablero, ArrayList<ArrayList<Object>> info) {

        if (tablero == null) {
            Output.sugerencia("Error: tablero no inicializado");
            return (null);
        }

        // String a devolver
        StringBuilder tableroPintado = new StringBuilder();

        // Se indica a tableroPintado su capacidad
        tableroPintado.ensureCapacity(caracteresPorLinea + numeroLineas);
        // Y se rellena de espacios
        for (int i = 0; i < caracteresPorLinea * numeroLineas; i++)
            tableroPintado.append(' ');
        // Y se insertar en su correspondiente lugar los saltos de línea
        for (int i = caracteresPorLinea - 1; i < caracteresPorLinea * numeroLineas; i += caracteresPorLinea)
            tableroPintado.setCharAt(i, '\n');

        // Se insertan las casillas tablero
        insertarFila(true, tableroPintado, tablero);
        insertarFila(false, tableroPintado, tablero);
        insertarColumnas(tableroPintado, tablero);    // Las columnas contenidas entre las filas superior e inferior

        // Se inserta la información a mostrar por pantalla
        if (info != null)
            insertarInfo(tableroPintado, info);

        corregirEspaciado(tableroPintado);

        return (tableroPintado.toString());

    }


    /**
     * Se inserta en el tablero a pintar la fila superior o inferior de casillas
     *
     * @param superior      si se debe insertar la fila superior o inferior de casillas
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param tablero       tablero que contiene las casillas a pintar
     */
    private static void insertarFila(boolean superior, StringBuilder stringBuilder, Tablero tablero) {

        int posicionIterada;    // Posición en el StringBuilder
        int posicionCasillaIterada;    // Posición de la casilla a iterar en Tablero
        Casilla casillaIterada = null;    // Casilla iterada

        // Si es la fila superior
        if (superior) {
            posicionIterada = 0;    // Se comienza a insertar desde el inicio
            posicionCasillaIterada = 20;
            casillaIterada = tablero.getCasillas().get(posicionCasillaIterada / casillasPorFila).get(
                    posicionCasillaIterada % casillasPorFila);

            // En caso contrario, es la fila inferior
        } else {
            // Se salta desde el inicio la suma de todas las casillas de un lado menos la última, junto con los
            // separadores intermedios, comenzándose a insertar la fila inferior desde el límite que comparte con las
            // casillas superiores
            posicionIterada = ((casillasPorLado - 1) * altoCasilla + separadoresPorLado - 2) * caracteresPorLinea;
            posicionCasillaIterada = 10;
            casillaIterada = tablero.getCasillas().get(posicionCasillaIterada / casillasPorFila).get(
                    posicionCasillaIterada % casillasPorFila);
        }

        // Se inserta la casilla izquierda
        insertarCasilla(stringBuilder, tablero, casillaIterada, posicionIterada, superior, !superior, true,
                false);

        // Se suman el ancho de la casilla y un separador, para insertar la casilla contigua desde la posición inicial
        // del separador que comparte con esta
        posicionIterada += anchoCasilla + 1;
        // En función de si es la fila superior o no, la casilla de la derecha será la siguiente o la anterior
        posicionCasillaIterada += superior ? 1 : -1;
        casillaIterada = tablero.getCasillas().get(posicionCasillaIterada / casillasPorFila).get(
                posicionCasillaIterada % casillasPorFila);

        // Se insertan las casillas intermedias
        for (int i = 1; i < casillasPorFila; i++) {
            insertarCasilla(stringBuilder, tablero, casillaIterada, posicionIterada, superior, !superior, false,
                    false);

            posicionIterada += anchoCasilla + 1;
            posicionCasillaIterada += superior ? 1 : -1;
            casillaIterada = tablero.getCasillas().get(posicionCasillaIterada / casillasPorFila).get(
                    posicionCasillaIterada % casillasPorFila);
        }

        // Se inserta la casilla derecha
        insertarCasilla(stringBuilder, tablero, casillaIterada, posicionIterada, superior, !superior, false,
                true);

    }


    /**
     * Se insertan en el tablero a pintar las columnas izquierda y derecha contenidas entre las filas superior e
     * inferior
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param tablero       tablero que contiene las casillas a pintar
     */
    private static void insertarColumnas(StringBuilder stringBuilder, Tablero tablero) {

        // Se comienzan a insertar las columnas desde el límite que comparten con la fila superior (una casilla más un
        // separador)
        int posicionIterada = (altoCasilla + 1) * caracteresPorLinea;
        Casilla casillaIterada = null;

        // Cada iteración inserta una fila de las columnas
        for (int i = 9; i > 0; i--) {

            // Casilla izquierda

            // Las casillas izquierdas son insertadas de mayor a menor en función del orden del tablero; las derechas
            // son insertadas de menor a mayor
            casillaIterada = tablero.getCasillas().get(1).get(i);
            insertarCasilla(stringBuilder, tablero, casillaIterada, posicionIterada, false, false,
                    true, false);

            // Casilla derecha

            // Se desplaza la posición en el número de casillas de un lado menos la última, junto con sus separadores
            // intermedios
            posicionIterada += ((casillasPorLado - 1) * anchoCasilla + separadoresPorLado - 2);
            casillaIterada = tablero.getCasillas().get(3).get(casillasPorFila - i);
            insertarCasilla(stringBuilder, tablero, casillaIterada, posicionIterada, false, false,
                    false, true);

            // Se sitúa en la siguiente casilla izquierda a insertar (1 separador + ancho de la casilla + 1 separador +
            // salto de línea + líneas del alto de la casilla)
            posicionIterada += 3 + anchoCasilla + altoCasilla * caracteresPorLinea;
        }


    }


    /**
     * Se inserta una casilla en el tablero a pintar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param tablero       tablero que contiene la casilla
     * @param casilla       casilla a pintar
     * @param posicion      posición de la esquina superior izquierda de la casilla en el tablero a pintar
     * @param superior      si es de la fila superior
     * @param inferior      si es de la fila inferior
     * @param izquierda     si es del lado izquierdo
     * @param derecha       si es del lado derecho
     */
    private static void insertarCasilla(StringBuilder stringBuilder, Tablero tablero, Casilla casilla, int posicion,
                                        boolean superior, boolean inferior, boolean izquierda, boolean derecha) {

        // Se pintan los límites de la casilla
        insertarLimites(stringBuilder, posicion);

        // Se inserta su nombre
        insertarNombre(stringBuilder, posicion, casilla.getNombre(), casilla.getGrupo().getTipo().getColor());

        // Se insertan los jugadores contenidos
        insertarJugadores(stringBuilder, posicion, casilla.getAvataresContenidos());

        // Se inserta el nombre de su propietario o, en su defecto, el precio de compra
        insertarPropietario(stringBuilder, tablero, posicion, casilla);

    }


    /**
     * Se inserta el recuadro de la casilla en el tablero a pintar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición de la esquina superior izquierda del recuadro en el tablero a pintar
     */
    private static void insertarLimites(StringBuilder stringBuilder, int posicion) {

        int iterante;    // Posición en el String
        int anchoTotal = anchoCasilla + 2;    // Incluyendo los separadores
        int altoTotal = altoCasilla + 2;    // Incluyendo los separadores

        // Se insertan los límites de la casilla ignorando por el momento las esquinas

        // El límite superior se comienza a insertar desde la posición inicial de la casilla más un carácter para no
        // modificar la esquina superior izquierda
        iterante = posicion + 1;
        insertarLimiteHorizontal(stringBuilder, iterante, anchoCasilla);

        // El límite inferior se comienza a insertar aumentando la posición inicial en el alto total menos un
        // separador, y sumando un carácter para no modificar la esquina inferior izquierda
        iterante = posicion + (altoTotal - 1) * caracteresPorLinea + 1;
        insertarLimiteHorizontal(stringBuilder, iterante, anchoCasilla);

        // El límite izquierdo se comienza a insertar sumando a la posición inicial una línea para no modificar la
        // esquina superior izquierda
        iterante = posicion + caracteresPorLinea;
        insertarLimiteVertical(stringBuilder, iterante, altoCasilla);

        // El límite derecho se comienza a insertar aumentando a la posición inicial el ancho total menos un carácter,
        // y sumándole una línea para no modificar la esquina superior derecha
        iterante = posicion + (anchoTotal - 1) + caracteresPorLinea;
        insertarLimiteVertical(stringBuilder, iterante, altoCasilla);

        // Ahora, se insertan las esquinas
        insertarEsquinaSuperiorIzquierda(stringBuilder, posicion);
        insertarEsquinaSuperiorDerecha(stringBuilder, posicion + (anchoTotal - 1));
        insertarEsquinaInferiorIzquierda(stringBuilder, posicion + (altoTotal - 1) * caracteresPorLinea);
        insertarEsquinaInferiorDerecha(stringBuilder, posicion + (anchoTotal - 1) + (altoTotal - 1) *
                caracteresPorLinea);


    }


    /**
     * Se inserta la esquina superior izquierda del recuadro en el tablero a pintar, "fusionando" el carácter que ya
     * exista con la esquina a representar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición en la que insertar la esquina en el tablero a pintar
     */
    private static void insertarEsquinaSuperiorIzquierda(StringBuilder stringBuilder, int posicion) {

        // Se comprueba si ya existe una carácter escrito por otra casilla
        char esquinaActual = stringBuilder.charAt(posicion);

        switch (esquinaActual) {

            case esquinaNOES:
            case esquinaNES:
            case esquinaOES:
            case esquinaES:
                break;

            case esquinaNO:
            case esquinaNOE:
            case esquinaNOS:
                stringBuilder.setCharAt(posicion, esquinaNOES);
                break;

            case barraVertical:
            case esquinaNE:
                stringBuilder.setCharAt(posicion, esquinaNES);
                break;

            case barraHorizontal:
            case esquinaOS:
                stringBuilder.setCharAt(posicion, esquinaOES);
                break;

            default:
                stringBuilder.setCharAt(posicion, esquinaES);
                break;

        }

    }


    /**
     * Se inserta la esquina superior derecha del recuadro en el tablero a pintar, "fusionando" el carácter que ya
     * exista con la esquina a representar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición en la que insertar la esquina en el tablero a pintar
     */
    private static void insertarEsquinaSuperiorDerecha(StringBuilder stringBuilder, int posicion) {

        // Se comprueba si ya existe una carácter escrito por otra casilla
        char esquinaActual = stringBuilder.charAt(posicion);

        switch (esquinaActual) {

            case esquinaNOS:
            case esquinaNOES:
            case esquinaOS:
            case esquinaOES:
                break;

            case barraVertical:
            case esquinaNO:
                stringBuilder.setCharAt(posicion, esquinaNOS);
                break;

            case esquinaNE:
            case esquinaNOE:
            case esquinaNES:
                stringBuilder.setCharAt(posicion, esquinaNOES);
                break;

            case barraHorizontal:
            case esquinaES:
                stringBuilder.setCharAt(posicion, esquinaOES);
                break;

            default:
                stringBuilder.setCharAt(posicion, esquinaOS);
                break;

        }

    }


    /**
     * Se inserta la esquina inferior izquierda del recuadro en el tablero a pintar, "fusionando" el carácter que ya
     * exista con la esquina a representar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición en la que insertar la esquina en el tablero a pintar
     */
    private static void insertarEsquinaInferiorIzquierda(StringBuilder stringBuilder, int posicion) {

        // Se comprueba si ya existe una carácter escrito por otra casilla
        char esquinaActual = stringBuilder.charAt(posicion);

        switch (esquinaActual) {

            case esquinaNE:
            case esquinaNOE:
            case esquinaNOES:
            case esquinaNES:
                break;

            case barraHorizontal:
            case esquinaNO:
                stringBuilder.setCharAt(posicion, esquinaNOE);
                break;

            case esquinaNOS:
            case esquinaOES:
                stringBuilder.setCharAt(posicion, esquinaNOES);
                break;

            case esquinaOS:
                stringBuilder.setCharAt(posicion, esquinaOES);
                break;

            case barraVertical:
            case esquinaES:
                stringBuilder.setCharAt(posicion, esquinaNES);
                break;

            default:
                stringBuilder.setCharAt(posicion, esquinaNE);
                break;

        }

    }


    /**
     * Se inserta la esquina inferior derecha del recuadro en el tablero a pintar, "fusionando" el carácter que ya
     * exista con la esquina a representar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición en la que insertar la esquina en el tablero a pintar
     */
    private static void insertarEsquinaInferiorDerecha(StringBuilder stringBuilder, int posicion) {

        // Se comprueba si ya existe una carácter escrito por otra casilla
        char esquinaActual = stringBuilder.charAt(posicion);

        switch (esquinaActual) {

            case esquinaNO:
            case esquinaNOE:
            case esquinaNOS:
            case esquinaNOES:
                break;

            case barraHorizontal:
            case esquinaNE:
                stringBuilder.setCharAt(posicion, esquinaNOE);
                break;

            case barraVertical:
            case esquinaOS:
                stringBuilder.setCharAt(posicion, esquinaNOS);
                break;

            case esquinaNES:
            case esquinaOES:
            case esquinaES:
                stringBuilder.setCharAt(posicion, esquinaNOES);
                break;

            default:
                stringBuilder.setCharAt(posicion, esquinaNO);
                break;

        }

    }


    /**
     * Se inserta la parte superior/inferior de un recuadro en el tablero a pintar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param iterante      posición en la que comenzar a insertar el límite en el tablero a representar
     * @param tamano        tamaño del límite a representar
     */
    private static void insertarLimiteHorizontal(StringBuilder stringBuilder, int iterante, int tamano) {

        for (int i = 0; i < tamano; i++)
            stringBuilder.setCharAt(iterante++, barraHorizontal);

    }


    /**
     * Se inserta la parte izquierda/derecha de un recuadro en el tablero a pintar
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param iterante      posición en la que comenzar a insertar el límite en el tablero a representar
     * @param tamano        tamaño del límite a representar
     */
    private static void insertarLimiteVertical(StringBuilder stringBuilder, int iterante, int tamano) {

        for (int i = 0; i < tamano; i++) {
            stringBuilder.setCharAt(iterante, barraVertical);
            iterante += caracteresPorLinea;
        }

    }


    /**
     * Se inserta una frase en el tablero a representar, resaltada en un color dado y centrada en la parte superior de
     * una casilla
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param posicion      posición de la esquina superior izquierda de la casilla en la que insertar la frase
     * @param nombre        frase a representar
     * @param color         color de fondo de la frase a representar
     */
    private static void insertarNombre(StringBuilder stringBuilder, int posicion, String nombre,
                                       TipoColor color) {

        int posicionEscritura;
        int charLibres;
        StringBuilder nombreConColor = new StringBuilder();

        // Cantidad de huecos que quedarán libres en el String, los cuales se emplearán para añadir espacios (debe
        // tenerse en cuenta el espacio ocupado por los códigos de color)
        charLibres = anchoCasilla - nombre.length() - color.getFondo().length() -
                TipoColor.resetAnsi.getFondo().length();

        // Se inserta el color de fondo
        nombreConColor.append(color.getFondo());

        // Se añaden los espacios libres al inicio del nombre (en caso de un total de espacios impar, se añade el
        // sobrante después del nombre)
        for (int i = 0; i < charLibres / 2; i++)
            nombreConColor.append(' ');

        // El nombre de la casilla
        nombreConColor.append(nombre);

        // Se añaden los espacios libres al final del nombre
        for (int i = 0; i < charLibres / 2 + charLibres % 2; i++)
            nombreConColor.append(' ');

        // Se quita el color de fondo
        nombreConColor.append(TipoColor.resetAnsi.getFondo());

        // Se sitúa en la esquina superior izquierda de la casilla (no en el separador)
        posicionEscritura = posicion + 1 + caracteresPorLinea;
        // Ahora se desplaza de modo que el texto quede centrado (dejando más espacios libres a la derecha que a la
        // izquierda si se da el caso)
        posicionEscritura += (anchoCasilla - nombreConColor.length()) / 2;
        // Se copian uno a uno los caracteres de la String creada, por lo que debe comprobarse si es menor la longitud
        // de esta o el espacio disponible
        int menor = (nombreConColor.length() <= anchoCasilla) ? nombreConColor.length() : anchoCasilla;

        for (int i = 0; i < menor; i++)
            stringBuilder.setCharAt(posicionEscritura++, nombreConColor.charAt(i));

    }


    /**
     * Se insertan los identificadores de unos avatares dados en el tablero a representar en la parte central de una
     * casilla
     *
     * @param stringBuilder      stringBuilder en el que se está representando el tablero
     * @param posicion           posición de la esquina superior izquierda de la casilla en la que insertar los identificadores
     * @param avataresContenidos avatares cuyos
     */
    private static void insertarJugadores(StringBuilder stringBuilder, int posicion, HashMap avataresContenidos) {

        int posicionEscritura;
        Collection valores = avataresContenidos.values();
        Iterator iterador = valores.iterator();
        StringBuilder avatares = new StringBuilder();

        // Se añaden los avatares
        while (iterador.hasNext()) {
            Avatar avatarIterado = (Avatar) iterador.next();
            avatares.append(avatarIterado.getIdentificador()).append(' ');
        }

        // Se sitúa en la primera columna libre (no en el separador) de la mitad de la casilla pintada
        posicionEscritura = posicion + 1 + caracteresPorLinea * (altoCasilla / 2 + altoCasilla % 2);

        // Se copian uno a uno los caracteres de la String creada, por lo que debe comprobarse si es menor la longitud
        // de esta o el espacio disponible
        int menor = (avatares.length() < anchoCasilla) ? avatares.length() : anchoCasilla;

        for (int i = 0; i < menor; i++)
            stringBuilder.setCharAt(posicionEscritura++, avatares.charAt(i));

    }


    /**
     * Se inserta, en caso de ser obtenible, el propietario de una casilla en el tablero a pintar en la parte inferior
     * de una casilla; de no haber sido comprada, se inserta su precio de compra
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param tablero       tablero que contiene la casilla a pintar
     * @param posicion      posición de la esquina superior izquierda de la casilla en la que insertar el propietario
     * @param casilla       casilla cuyo propietario se va a representar
     */
    private static void insertarPropietario(StringBuilder stringBuilder, Tablero tablero, int posicion, Casilla
            casilla) {

        int posicionEscritura;
        StringBuilder propietario = new StringBuilder();

        // Si la casilla no tiene un propietario
        if (casilla.getPropietario().equals(tablero.getBanca())) {

            int precio = 0;

            // Si es una casilla de servicios o de transporte, el precio no es repartido entre los integrantes del
            // grupo
            if (casilla.getGrupo().getTipo() == TipoGrupo.servicios || casilla.getGrupo().getTipo() ==
                    TipoGrupo.transporte)
                precio = casilla.getGrupo().getPrecio();
            else
                precio = (int) (casilla.getGrupo().getPrecio() / (double) casilla.getGrupo().getCasillas().size());

            // Debe diferenciarse entre aquellas casillas que tengan un precio asociado y aquellas que no (como las
            // de suerte o de comunidad)
            if (precio <= 0) return;

            //DecimalFormat decimal = new DecimalFormat(".##");
            //propietario.append(decimal.format(precio)).append("K €");
            propietario.append(precio).append("K €");    // Supone un incremento de 5 ms

        } else
            propietario.append("Prop.: ").append(casilla.getPropietario().getNombre());


        // Se sitúa en la esquina inferior izquierda de la casilla (no en el separador)
        posicionEscritura = posicion + 1 + altoCasilla * caracteresPorLinea;

        // Se copian uno a uno los caracteres de la String creada, por lo que debe comprobarse si es menor la longitud
        // de esta o el espacio disponible
        int menor = (propietario.length() < anchoCasilla) ? propietario.length() : anchoCasilla;

        for (int i = 0; i < menor; i++)
            stringBuilder.setCharAt(posicionEscritura++, propietario.charAt(i));

    }


    /**
     * Se inserta un conjunto de información en el tablero a pintar, aprovechando el espacio libre en el interior del
     * tablero y de modo que quede centrada
     *
     * @param stringBuilder stringBuilder en el que se está representando el tablero
     * @param info información a representar, conformada por tuplas que contienen, en orden, un String con la
     *             información, un entero con el ancho total del String, y un entero con el alto total
     */
    private static void insertarInfo(StringBuilder stringBuilder, ArrayList<ArrayList<Object>> info) {

        // Tamaño del tablero en caracteres
        int anchoTablero = caracteresPorLinea - 1;    // No se contabiliza el carácter de salto de línea
        int altoTablero = numeroLineas;

        // Número de recuadros de información a imprimir
        int numeroInfo = info.size();

        // Las dimensiones totales
        int anchoTotal = 0;
        int altoTotal = 0;
        // Las dimensiones máximas de los recuadros a imprimir
        int anchoMaximo = 0;
        int altoMaximo = 0;

        // Las líneas y columnas que quedarán libres tras imprimir los recuadros
        int columnasLibres = 0;
        int lineasLibres = 0;

        // Las separaciones horizontal y vertical en función del número de recuadros a imprimir y el espacio libre
        int separacionHorizontal = 0;
        int separacionVertical = 0;

        // La posición de escritura inicial
        int posicionEscrituraInicial = 0;


        // Se calculan las dimensiones totales y las máximas
        for (ArrayList<Object> tupla : info) {

            int anchoLeido = (Integer) tupla.get(1);
            int altoLeido = (Integer) tupla.get(2);

            anchoTotal += anchoLeido;
            if (anchoLeido > anchoMaximo)
                anchoMaximo = anchoLeido;

            altoTotal += altoLeido;
            if (altoLeido > altoMaximo)
                altoMaximo = altoLeido;

        }

        // Se calculan las líneas y columnas que quedarán libres
        columnasLibres = anchoDisponible - anchoTotal;
        lineasLibres = altoDisponible - altoMaximo;


        // Se calcula la posición de escritura inicial

        // Se sitúa la posición de escritura inicial en la esquina superior izquierda del interior del tablero,
        // avanzando primero en vertical y a continuación en horizontal
        posicionEscrituraInicial = (altoCasilla + 2) * caracteresPorLinea;    // Casillas superiores y 2 separadores
        posicionEscrituraInicial += anchoCasilla + 2;    // Casillas izquierdas y 2 separadores

        // Se separarán los recuadros equitativamente, en función del tamaño disponible dejando, si es posible, espacio
        // entre ellos y los límites interiores del tablero
        separacionHorizontal = columnasLibres / (info.size() + 1);
        separacionVertical = lineasLibres / 2;

        posicionEscrituraInicial += separacionVertical * caracteresPorLinea;
        posicionEscrituraInicial += separacionHorizontal;

        // Se insertan los recuadros
        for (int i = 0; i < info.size(); i++) {

            // Información del recuadro a insertar
            String informacion = (String) info.get(i).get(0);

            // Número de caracteres insertados de la información
            int numeroCaracteresInsertados = 0;

            // Dimensiones del recuadro a insertar
            int anchoIterado = (Integer) info.get(i).get(1);
            int altoIterado = (Integer) info.get(i).get(2);

            // Se calcula la posición de escritura del recuadro iterado a partir de la posición de escritura inicial
            int posicionEscritura = posicionEscrituraInicial;

            // El avance horizontal depende de los recuadros impresos anteriormente
            for (int j = 0; j < i; j++)
                posicionEscritura += (Integer) info.get(j).get(1) + separacionHorizontal;

            // El avance vertical depende del alto del recuadro a imprimir
            posicionEscritura += (altoMaximo - altoIterado) / 2 * caracteresPorLinea;

            // Se inserta la información, evitando los saltos de línea
            for (int j = 0; j < informacion.length(); j++) {

                char caracterIterado = informacion.charAt(j);

                // Si es un salto de línea, se retrocede y se va a la siguiente línea
                if (caracterIterado == '\n') {
                    posicionEscritura -= numeroCaracteresInsertados;
                    numeroCaracteresInsertados = 0;
                    posicionEscritura += caracteresPorLinea;
                }

                // En caso contrario, se inserta en el tablero a pintar
                else {
                    stringBuilder.setCharAt(posicionEscritura++, caracterIterado);
                    numeroCaracteresInsertados++;
                }
            }
        }
    }


    /**
     * Se recorre el tablero a pintar, insertando en aquellas posiciones en las que se encuentren secuencias ANSI de
     * colores los espacios faltantes, dado que estas ocupan espacio que se había creado para la representación del
     * tablero
     *
     * @param stringBuilder tablero a pintar
     */
    private static void corregirEspaciado(StringBuilder stringBuilder) {

        // Al ser tratado el tablero como un mapa de char, los colores establecidos toman parte de este espacio,
        // descolocando la posición del resto de elementos al reemplazar los espacios; es por ello que se recorre el
        // tablero insertando estos allí donde deberían estar
        boolean reseteoColor = true;

        for (int i = 0; i < stringBuilder.length(); i++) {

            if (stringBuilder.charAt(i) == '[') {

                reseteoColor = stringBuilder.charAt(i + 1) == '0' && stringBuilder.charAt(i + 2) == '0';

                // Si es la cadena para resetar el color, se añaden antes
                if (!reseteoColor) {
                    i += 4;
                    stringBuilder.insert(i, "     ");
                    i += 5;
                }

                // En caso contrario, se añaden después
                else {
                    i -= 1;
                    stringBuilder.insert(i, "     ");
                    i += 10;
                }
            }
        }
    }
}
