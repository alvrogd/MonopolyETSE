package aplicacion.salidaPantalla;

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

        /*if (tablero == null) {
            System.out.println("Error: tablero no inicializado");
            return (null);
        }
        if (escalado <= 0) {
            System.out.println("Error: el escalado debe ser mayor que 0");
            return (null);
        }*/

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


        return (tableroPintado.toString());

    }


    private static void insertarFilaSuperior(StringBuilder stringBuilder) {

        insertarCasilla(stringBuilder, 0, true, false, true, false,
                true);
        insertarCasilla(stringBuilder,anchoEsquina + 1, true, false, false,
                false, false);

    }


    private static void insertarCasilla(StringBuilder stringBuilder, int posicion, boolean superior, boolean inferior,
                                        boolean izquierda, boolean derecha, boolean esquina) {

        insertarLimites(stringBuilder, posicion, superior, inferior, izquierda, derecha, esquina);


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

    /*private static void insertarLimiteHorizontal(StringBuilder stringBuilder, int iterante, boolean bold) {

        // Posición desde la que comenzar a insertar el límite superior
        int posicion;

        if (superior)
            posicion = 0;
        else
            // Se saltan todas las líneas excepto la última, restando 1 dado que el índice inicial es 0
            posicion = (numeroLineas - 1) * caracteresPorLinea - 1;

        // Se resta 1 a caracteresPorLinea para no reemplazar el salto de línea
        for (int i = 0; i < caracteresPorLinea - 1; i++)
            stringBuilder.setCharAt(posicion++, '━');

    }*/


}
