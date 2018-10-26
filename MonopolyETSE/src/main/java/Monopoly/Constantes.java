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

    // Incremento del precio de un grupo de solares respecto al grupo previo
    public final static double INCREMENTO_GRUPO = 0.30;
    // Precio inicial del primero grupo de casillas en miles
    public final static double PRECIO_INICIAL_0 = 1200;
    public final static double PRECIO_INICIAL_1 = PRECIO_INICIAL_0 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_2 = PRECIO_INICIAL_1 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_3 = PRECIO_INICIAL_2 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_4 = PRECIO_INICIAL_3 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_5 = PRECIO_INICIAL_4 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_6 = PRECIO_INICIAL_5 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_7 = PRECIO_INICIAL_6 * ( 1 + INCREMENTO_GRUPO );
    public final static double PRECIO_INICIAL_8 = PRECIO_INICIAL_7 * ( 1 + INCREMENTO_GRUPO );

    //




}
