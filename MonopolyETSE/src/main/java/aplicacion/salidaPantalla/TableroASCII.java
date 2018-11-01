package aplicacion.salidaPantalla;

import monopoly.tablero.Tablero;

public class TableroASCII {

    /* Atributos */

    // Número de caracteres de una unidad
    private final static int caracteresPorUnidad = 2;

    // Tamaño de las casillas
    private final static int anchoEsquina = 7 * caracteresPorUnidad;
    private final static int altoEsquina = 2 * caracteresPorUnidad;
    private final static int anchoNormal = 7 * caracteresPorUnidad;
    private final static int altoNormal = 2 * caracteresPorUnidad;

    // Número de casillas por cada fila
    private final static int casillasPorFila = 10;
    // Número de separadores por cada fila de casillas
    private final static int separadoresPorFila = casillasPorFila + 2;

    // Caracteres por línea (2 esquinas, 9 normales, separadores y salto de línea)
    private final static int caracteresPorLinea = 2 * anchoEsquina + 9 * anchoNormal + separadoresPorFila + 1;
    // Número de líneas
    private final static int numeroLineas = 2 * altoEsquina + 9 * altoNormal + separadoresPorFila;



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

        insertarCasilla(stringBuilder, 0, true, false, true, false, true);

    }


    private static void insertarCasilla(StringBuilder stringBuilder, int posicion, boolean superior, boolean inferior,
                                        boolean izquierda, boolean derecha, boolean esquina) {

        int iterante;
        int ancho;
        int alto;

        // Se obtiene los correspondientes anchos y altos de la casilla a insertar
        if (esquina) {
            ancho = anchoEsquina + 2;    // Incluyendo los separadores
            alto = altoEsquina + 2;    // Incluyendo los separadores
        } else {
            ancho = anchoNormal + 2;
            alto = altoNormal + 2;
        }

        // El límite superior se comienza a insertar desde la posición inicial de la casilla
        iterante = posicion;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho, superior);

        // El límite inferior se comienza a insertar aumentando la posición inicial en el alto total menos una línea
        iterante = posicion + (alto - 1) * caracteresPorLinea;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho, inferior);

        // El límite izquierdo se comienza a insertar desde la posición inicial de la casilla
        iterante = posicion;
        insertarLimiteVertical(stringBuilder, iterante, alto, izquierda);

        // El límite derecho se comienza a insertar aumentando a la posición inicial el ancho total menos un carácter
        iterante = posicion + (ancho - 1);
        insertarLimiteVertical(stringBuilder, iterante, alto, derecha);


    }


    private static void insertarLimites( StringBuilder stringBuilder, int posicion, boolean superior, boolean inferior,
                                         boolean izquierda, boolean derecha, boolean esquina) {

        int iterante;
        int ancho;
        int alto;

        // Se obtiene los correspondientes anchos y altos de la casilla a insertar
        if (esquina) {
            ancho = anchoEsquina + 2;    // Incluyendo los separadores
            alto = altoEsquina + 2;    // Incluyendo los separadores
        } else {
            ancho = anchoNormal + 2;
            alto = altoNormal + 2;
        }

        // El límite superior se comienza a insertar desde la posición inicial de la casilla
        iterante = posicion;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho, superior);

        // El límite inferior se comienza a insertar aumentando la posición inicial en el alto total menos una línea
        iterante = posicion + (alto - 1) * caracteresPorLinea;
        insertarLimiteHorizontal(stringBuilder, iterante, ancho, inferior);

        // El límite izquierdo se comienza a insertar desde la posición inicial de la casilla
        iterante = posicion;
        insertarLimiteVertical(stringBuilder, iterante, alto, izquierda);

        // El límite derecho se comienza a insertar aumentando a la posición inicial el ancho total menos un carácter
        iterante = posicion + (ancho - 1);
        insertarLimiteVertical(stringBuilder, iterante, alto, derecha);

    }


    private static void insertarLimiteHorizontal(StringBuilder stringBuilder, int iterante, int tamano, boolean bold) {

        if (bold)
            for (int i = 0; i < tamano; i++)
                stringBuilder.setCharAt(iterante++, '━');
        else
            for (int i = 0; i < tamano; i++)
                stringBuilder.setCharAt(iterante++, '─');

    }

    private static void insertarLimiteVertical(StringBuilder stringBuilder, int iterante, int tamano, boolean bold) {

        if (bold)
            for (int i = 0; i < tamano; i++) {
                stringBuilder.setCharAt(iterante, '┃');
                iterante += caracteresPorLinea;
            }
        else
            for (int i = 0; i < tamano; i++) {
                stringBuilder.setCharAt(iterante, '│');
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
