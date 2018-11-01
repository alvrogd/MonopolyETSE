package aplicacion.salidaPantalla;

import monopoly.tablero.Tablero;

public class TableroASCII {

    /* Atributos */

    // Número de caracteres de una unidad
    private final static int caracteresPorUnidad = 5;

    // Tamaño de las casillas
    private final static int anchoEsquina = 3 * caracteresPorUnidad;
    private final static int altoEsquina = 3 * caracteresPorUnidad;
    private final static int anchoNormal = 3 * caracteresPorUnidad;
    private final static int altoNormal = 3 * caracteresPorUnidad;

    // Número de casillas por cada fila
    private final static int casillasPorFila = 10;
    // Número de separadores por cada fila de casillas
    private final static int separadoresPorFila = casillasPorFila + 2;



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

        // Caracteres por línea (2 esquinas, 9 normales, separadores y salto de línea)
        final int caracteresPorLinea = 2 * anchoEsquina + 9 * anchoNormal + separadoresPorFila + 1;
        // Número de líneas
        final int numeroLineas = 2 * altoEsquina + 9 * altoNormal + separadoresPorFila;

        // Se indica a tableroPintado su capacidad
        tableroPintado.ensureCapacity(caracteresPorLinea + numeroLineas);
        // Y se rellena de espacios
        for (int i = 0; i < caracteresPorLinea * numeroLineas; i++)
            tableroPintado.append(' ');
        // Y se insertar en su correspondiente lugar los saltos de línea
        for( int i = caracteresPorLinea - 1; i < caracteresPorLinea * numeroLineas; i += caracteresPorLinea )
            tableroPintado.setCharAt(i, '\n');

        // Se insertan las filas al tablero
        insertarFilaSuperior(tableroPintado, caracteresPorLinea, numeroLineas);


        return (tableroPintado.toString());

    }


    private static void insertarFilaSuperior(StringBuilder stringBuilder, int caracteresPorLinea, int numeroLineas) {


    }

}
