package aplicacion.salidaPantalla;

import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;

public class TableroASCII {

    /* Atributos */

    // Número de caracteres de una unidad
    private final static int caracteresPorUnidad = 3;

    // Tamaño de las casillas
    private final static int anchoEsquina = 5 * caracteresPorUnidad;
    private final static int altoEsquina = 1 * caracteresPorUnidad;
    private final static int anchoNormal = 5 * caracteresPorUnidad;
    private final static int altoNormal = 1 * caracteresPorUnidad;

    // Número de casillas por cada fila
    private final static int casillasPorFila = 10;
    // Número de separadores por cada fila de casillas
    private final static int separadoresPorFila = casillasPorFila + 2;

    // Caracteres por línea (2 esquinas, 9 normales, separadores y salto de línea)
    private final static int caracteresPorLinea = 2 * anchoEsquina + 9 * anchoNormal + separadoresPorFila + 1;
    // Número de líneas
    private final static int numeroLineas = 2 * altoEsquina + 9 * altoNormal + separadoresPorFila;

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


    /* Métodos */

    public static String pintaTablero(Tablero tablero, int escalado) {

        if (tablero == null) {
            System.out.println("Error: tablero no inicializado");
            return (null);
        }
        if (escalado <= 0) {
            System.out.println("Error: el escalado debe ser mayor que 0");
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

        // Se insertan las filas al tablero
        insertarFilaSuperior(tableroPintado);

        corregirEspaciado(tableroPintado);

        return (tableroPintado.toString());

    }


    private static void insertarFilaSuperior(StringBuilder stringBuilder, Tablero tablero) {

        /*insertarCasilla(stringBuilder, 0, true, false, true, false,
                true, "C1", TipoColor.rojoANSI);
        insertarCasilla(stringBuilder, anchoEsquina + 1, true, false, false,
                false, false, "C2", TipoColor.azulANSI);*/
        int posicionIterada = 0;
        Casilla casillaIterada = tablero.getCasillas().get(20/10).get(20%10);

        // Se inserta la casilla superior izquierda
        insertarCasilla(stringBuilder, posicionIterada, true, false, true, false,
                true, casillaIterada.getNombre(), casillaIterada.getGrupo().getTipo().get);

        for (int i = 0; i < casillasPorFila; i++) {
            insertarCasilla(stringBuilder, );

        }

    }


    private static void insertarCasilla(StringBuilder stringBuilder, int posicion, boolean superior, boolean inferior,
                                        boolean izquierda, boolean derecha, boolean esquina, String nombre, TipoColor
                                                color) {

        // Se pintan los límites de la casilla
        insertarLimites(stringBuilder, posicion, superior, inferior, izquierda, derecha, esquina);

        // Se imprime su nombre
        insertarNombre(stringBuilder, posicion, esquina, nombre, color);


    }


    private static void insertarLimites(StringBuilder stringBuilder, int posicion, boolean superior, boolean inferior,
                                        boolean izquierda, boolean derecha, boolean esquina) {

        int iterante;
        int ancho;
        int alto;
        int anchoTotal;
        int altoTotal;

        // Se obtiene los correspondientes anchos y altos de la casilla a insertar
        if (esquina) {
            ancho = anchoEsquina;
            alto = altoEsquina;
        } else {
            ancho = anchoNormal;
            alto = altoNormal;
        }

        anchoTotal = ancho + 2;    // Incluyendo los separadores
        altoTotal = alto + 2;    // Incluyendo los separadores

        // Se insertan los límites de la casilla ignorando por el momento las esquinas

        // El límite superior se comienza a insertar desde la posición inicial de la casilla más un carácter para no
        // modificar la esquina superior izquierda
        iterante = posicion + 1;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho);

        // El límite inferior se comienza a insertar aumentando la posición inicial en el alto total menos una línea, y
        // sumando un carácter para no modificar la esquina inferior izquierda
        iterante = posicion + (altoTotal - 1) * caracteresPorLinea + 1;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho);

        // El límite izquierdo se comienza a insertar sumando a la posición inicial una línea para no modificar la
        // esquina superior izquierda
        iterante = posicion + caracteresPorLinea;
        insertarLimiteVertical(stringBuilder, iterante, alto);

        // El límite derecho se comienza a insertar aumentando a la posición inicial el ancho total menos un carácter,
        // y sumándole una línea para no modificar la esquina superior derecha
        iterante = posicion + (anchoTotal - 1) + caracteresPorLinea;
        insertarLimiteVertical(stringBuilder, iterante, alto);

        // Ahora, se insertan las esquinas

        insertarEsquinaSuperiorIzquierda(stringBuilder, posicion);
        insertarEsquinaSuperiorDerecha(stringBuilder, posicion + (anchoTotal - 1));
        insertarEsquinaInferiorIzquierda(stringBuilder, posicion + (altoTotal - 1) * caracteresPorLinea);
        insertarEsquinaInferiorDerecha(stringBuilder, posicion + (altoTotal - 1) * caracteresPorLinea +
                (anchoTotal - 1));


    }

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

            case esquinaOS:
                stringBuilder.setCharAt(posicion, esquinaOES);

            case barraVertical:
            case esquinaES:
                stringBuilder.setCharAt(posicion, esquinaNES);

            default:
                stringBuilder.setCharAt(posicion, esquinaNE);
                break;

        }

    }

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

            case esquinaNES:
            case esquinaOES:
            case esquinaES:
                stringBuilder.setCharAt(posicion, esquinaNOES);

            default:
                stringBuilder.setCharAt(posicion, esquinaNO);
                break;

        }

    }


    private static void insertarLimiteHorizontal(StringBuilder stringBuilder, int iterante, int tamano) {

        for (int i = 0; i < tamano; i++)
            stringBuilder.setCharAt(iterante++, barraHorizontal);

    }

    private static void insertarLimiteVertical(StringBuilder stringBuilder, int iterante, int tamano) {

        for (int i = 0; i < tamano; i++) {
            stringBuilder.setCharAt(iterante, barraVertical);
            iterante += caracteresPorLinea;
        }

    }

    private static void insertarNombre(StringBuilder stringBuilder, int posicion, boolean esquina, String nombre,
                                       TipoColor color) {

        int ancho;
        int alto;
        int posicionEscritura;
        int charLibres;

        // todo comprobación de que la cadena no sea demasiado larga
        // Se obtiene los correspondientes anchos y altos de la casilla a insertar
        if (esquina) {
            ancho = anchoEsquina;
            alto = altoEsquina;
        } else {
            ancho = anchoNormal;
            alto = altoNormal;
        }

        // Y se guarda el nombr

        // Variable en la que guardar el nombre con color
        StringBuilder nombreConColor = new StringBuilder();
        // Cantidad de huecos que quedarán libres en el String, los cuales se emplearán para añadir espacios
        charLibres = ancho - nombre.length() - color.getFondo().length() - TipoColor.resetAnsi.getFondo().length();

        // Color de fondo
        nombreConColor.append(color.getFondo());

        // Se añaden los espacios libres al inicio del nombre
        for (int i = 0; i < charLibres / 2; i++)
            nombreConColor.append(' ');

        // Nombre
        nombreConColor.append(nombre);

        // Se añaden los espacios libres al final del nombre
        for (int i = 0; i < charLibres / 2 + charLibres % 2; i++)
            nombreConColor.append(' ');

        // Se quita el color de fondo
        nombreConColor.append(TipoColor.resetAnsi.getFondo());

        // Se sitúa en la primera columna de la primera línea libre de la casilla pintada
        posicionEscritura = posicion + caracteresPorLinea + 1;
        // Ahora se desplaza de modo que el texto quede centrado
        posicionEscritura += (ancho - nombreConColor.length()) / 2;
        // Se copian uno a uno los caracteres de la String creada, por lo que debe comprobarse si es menor la longitud
        // de esta o el espacio disponible
        int menor = (nombreConColor.toString().length() < ancho) ? nombreConColor.toString().length() : ancho;

        for (int i = 0; i < menor; i++)
            stringBuilder.setCharAt(posicionEscritura++, nombreConColor.toString().charAt(i));

    }

    private static void corregirEspaciado(StringBuilder stringBuilder) {

        // En caso de que se encuentre un color de fondo en el String, deben añadirse los espacios tras este para que
        // también se vean coloreados; en caso contrario, deben introducirse antes del reseteo de color
        boolean reseteoColor = true;

        for (int i = 0; i < stringBuilder.toString().length(); i++) {

            if (stringBuilder.charAt(i) == '[') {

                reseteoColor = !reseteoColor;

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
