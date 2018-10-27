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
    public final static double PRECIO_INICIAL_GRUPO_0 = 1200;
    public final static double PRECIO_INICIAL_GRUPO_1 = PRECIO_INICIAL_GRUPO_0 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_2 = PRECIO_INICIAL_GRUPO_1 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_3 = PRECIO_INICIAL_GRUPO_2 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_4 = PRECIO_INICIAL_GRUPO_3 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_5 = PRECIO_INICIAL_GRUPO_4 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_6 = PRECIO_INICIAL_GRUPO_5 * (1 + INCREMENTO_GRUPO);
    public final static double PRECIO_INICIAL_GRUPO_7 = PRECIO_INICIAL_GRUPO_6 * (1 + INCREMENTO_GRUPO);

    //Precio edificios
    public final static double COEF_CASA = 0.60;
    public final static double COEF_HOTEL = 0.60;
    public final static double COEF_PISCINA = 0.40;
    public final static double COEF_PISTADEPORTE = 1.25;

    // Suma del precio de todos los solares
    public final static double SUMA_PRECIO_SOLARES = 2 * PRECIO_INICIAL_GRUPO_0 + 3 * PRECIO_INICIAL_GRUPO_1 + 3 *
            PRECIO_INICIAL_GRUPO_2 + 3 * PRECIO_INICIAL_GRUPO_4 + 3 * PRECIO_INICIAL_GRUPO_5 + 3 *
            PRECIO_INICIAL_GRUPO_6 + 3 * PRECIO_INICIAL_GRUPO_7;

    // Dinero inicial para cada jugador
    public final static double DINERO_INICIAL = SUMA_PRECIO_SOLARES / 3;

    // Dinero de la casilla de salida
    public final static double DINERO_SALIDA = SUMA_PRECIO_SOLARES / NUMERO_SOLARES;

    // Factor con el que determinar el precio de una casilla de servicio
    public final static double FACTOR_SERVICIO = DINERO_SALIDA / 200;

    // Importe a pagar para salir de la cárcel
    public final static double DINERO_CARCEL = DINERO_SALIDA / 4;

    // Importe de las casillas de impuestos
    public final static double IMPUESTO_1 = DINERO_SALIDA;
    public final static double IMPUESTO_2 = DINERO_SALIDA / 2;


}
