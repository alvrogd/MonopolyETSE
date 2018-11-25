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

    //Alquiler con edificios
    public final static double COEF_ALQUILER = 0.10;
    public final static int ALQ_UNACASA = 5;
    public final static int ALQ_DOSCASA = 15;
    public final static int ALQ_TRESCASA = 35;
    public final static int ALQ_CUATROCASA = 50;
    public final static int ALQ_HOTEL = 70;
    public final static int ALQ_PISCINA = 25;
    public final static int ALQ_PISTADEPORTE = 25;

    // Suma del precio de todos los solares
    public final static int SUMA_PRECIO_SOLARES = (PRECIO_INICIAL_GRUPO_0 + PRECIO_INICIAL_GRUPO_1 +
            PRECIO_INICIAL_GRUPO_2 + PRECIO_INICIAL_GRUPO_4 +PRECIO_INICIAL_GRUPO_5 +
            PRECIO_INICIAL_GRUPO_6 + PRECIO_INICIAL_GRUPO_7);

    // Dinero inicial para cada jugador
    public final static int DINERO_INICIAL = (int) (SUMA_PRECIO_SOLARES / (double) 3);

    // Dinero de la casilla de salida
    public final static int DINERO_SALIDA = (int) (SUMA_PRECIO_SOLARES / (double) NUMERO_SOLARES);
    // Dinero de la casilla de salida en caso de moverse por una carta
    public final static int DINERO_SALIDA_CARTA = 2000;

    // Factor con el que determinar el precio de una casilla de servicio
    public final static int FACTOR_SERVICIO = (int) (DINERO_SALIDA / (double) 200);

    // Importe a pagar para salir de la cárcel
    public final static int DINERO_CARCEL = (int) (DINERO_SALIDA / (double) 4);

    // Importe de las casillas de impuestos
    public final static int IMPUESTO_1 = DINERO_SALIDA;
    public final static int IMPUESTO_2 = (int) (DINERO_SALIDA / (double) 2);

    // Posición de casillas clave
    public final static int POSICION_CARCEL = 10;
    public final static int POSICION_PARKING = 20;

    // Precio de las casillas de transporte
    public final static int DINERO_TRANSPORTES = DINERO_SALIDA;
    // Precio de las casillas de servicio
    public final static int DINERO_SERVICIOS = (int) (DINERO_SALIDA * 0.75);

    // Máximo número de turnos en la cárcel
    public final static int MAX_TURNOS_CARCEL = 3;

    public final static int NUM_CARTAS_SUERTE = 14;
    public final static int NUM_CARTAS_COMUNIDAD = 10;
    /* No se implementan los getters de los atributos aquí declarados dado que es una clase de uso exclusivo para
     * constantes del programa y, por lo tanto, todos pueden ser accedidos directamente desde fuera
     */

}
