/*
 * Archivo: Constantes.java
 * Autores: Álvaro Goldar Dieste, Francisco Javier Cardama
 * Fecha de creación: 25/10/2018 - 01:22
 */

package monopoly;

public final class Constantes {

    // Porcentaje de incremento del valor de los solares sin vender cuando los jugadores completen
    // NUMERO_VUELTAS_INCREMENTO
    public final static double INCREMENTO_VUELTAS = 0.05;
    public final static int NUMERO_VUELTAS_INCREMENTO = 4;

    // Número de solares
    public final static int NUMERO_SOLARES = 25;
    // Incremento del precio de un grupo de solares respecto al grupo previo
    public final static double INCREMENTO_GRUPO = 0.30;
    // Precio inicial de los grupos de casillas en miles
    public final static int PRECIO_INICIAL_GRUPO_0 = 1200;
    public final static int PRECIO_INICIAL_GRUPO_1 = (int) (PRECIO_INICIAL_GRUPO_0 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_2 = (int) (PRECIO_INICIAL_GRUPO_1 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_3 = (int) (PRECIO_INICIAL_GRUPO_2 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_4 = (int) (PRECIO_INICIAL_GRUPO_3 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_5 = (int) (PRECIO_INICIAL_GRUPO_4 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_6 = (int) (PRECIO_INICIAL_GRUPO_5 * (1 + INCREMENTO_GRUPO));
    public final static int PRECIO_INICIAL_GRUPO_7 = (int) (PRECIO_INICIAL_GRUPO_6 * (1 + INCREMENTO_GRUPO));

    //Precio edificios
    public final static double COEF_CASA = 0.60;
    public final static double COEF_HOTEL = 0.60;
    public final static double COEF_PISCINA = 0.40;
    public final static double COEF_PISTADEPORTE = 1.25;

    // Suma del precio de todos los solares
    public final static int SUMA_PRECIO_SOLARES = (int) (2 * PRECIO_INICIAL_GRUPO_0 + 3 * PRECIO_INICIAL_GRUPO_1 + 3 *
            PRECIO_INICIAL_GRUPO_2 + 3 * PRECIO_INICIAL_GRUPO_4 + 3 * PRECIO_INICIAL_GRUPO_5 + 3 *
            PRECIO_INICIAL_GRUPO_6 + 3 * PRECIO_INICIAL_GRUPO_7);

    // Dinero inicial para cada jugador
    public final static int DINERO_INICIAL = (int) (SUMA_PRECIO_SOLARES / 3);

    // Dinero de la casilla de salida
    public final static int DINERO_SALIDA = (int) (SUMA_PRECIO_SOLARES / NUMERO_SOLARES);

    // Factor con el que determinar el precio de una casilla de servicio
    public final static int FACTOR_SERVICIO = (int) (DINERO_SALIDA / 200);

    // Importe a pagar para salir de la cárcel
    public final static int DINERO_CARCEL = (int) (DINERO_SALIDA / 4);

    // Importe de las casillas de impuestos
    public final static int IMPUESTO_1 = DINERO_SALIDA;
    public final static int IMPUESTO_2 = (int) (DINERO_SALIDA / 2);

    // Posición de casillas clave
    public final static int POSICION_CARCEL = 10;
    public final static int POSICION_PARKING = 20;

}
