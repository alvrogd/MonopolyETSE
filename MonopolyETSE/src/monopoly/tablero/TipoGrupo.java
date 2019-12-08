package monopoly.tablero;

import aplicacion.salidaPantalla.TipoColor;
import monopoly.Constantes;

public enum TipoGrupo {

    /*Tipos de enumeraciones*/
    negro("solar",Constantes.PRECIO_INICIAL_GRUPO_0, TipoColor.negroANSI, 2),
    cyan("solar",Constantes.PRECIO_INICIAL_GRUPO_1, TipoColor.cianANSI, 3),
    rosa("solar",Constantes.PRECIO_INICIAL_GRUPO_2, TipoColor.violetaANSI,3),
    naranja("solar",Constantes.PRECIO_INICIAL_GRUPO_3, TipoColor.amarilloANSI, 3),
    rojo("solar",Constantes.PRECIO_INICIAL_GRUPO_4, TipoColor.rojoANSI, 3),
    marron("solar",Constantes.PRECIO_INICIAL_GRUPO_5, TipoColor.blancoANSI, 3),
    verde("solar",Constantes.PRECIO_INICIAL_GRUPO_6, TipoColor.verdeANSI,3 ),
    azul("solar",Constantes.PRECIO_INICIAL_GRUPO_7, TipoColor.azulANSI, 2),
    transporte("transporte",Constantes.DINERO_TRANSPORTES, TipoColor.resetAnsi,4),
    servicios("servicio",Constantes.DINERO_SERVICIOS, TipoColor.resetAnsi,2);

    /*Atributos*/

    //Precio inicial del grupo
    private int precioInicial;

    //El color con el que se imprimirá el grupo
    private final TipoColor color;

    //String con el tipo de casilla que llevará.
    private final String tipoCasilla;

    // Tamaño del grupo
    private final int tamano;

    /**
     * Constructor para grupos que no tengan un importe relacionado, el cual se establece a -10
     * @param tipocasilla se establece el tipo de casilla
     * @param color el color del grupo
     */
    private TipoGrupo(String tipocasilla,TipoColor color, int tamano){
        this.precioInicial = -10;
        this.color = color;
        this.tipoCasilla = tipocasilla;
        this.tamano = tamano;
    }

    /**
     * Constructor para grupos que tengan un importe relacionado
     * @param tipocasilla se establece el tipo de casilla
     * @param precioInicial importe a establecer para el grupo
     * @param color color del grupo
     */
    private TipoGrupo(String tipocasilla, int precioInicial, TipoColor color, int tamano) {

        this.tipoCasilla = tipocasilla;
        this.precioInicial = precioInicial;
        this.color = color;
        this.tamano = tamano;
    }

    /*Getters y Setters*/
    public int getPrecioInicial() {

        return precioInicial;

    }

    public void setPrecioInicial(int precioInicial) {
        this.precioInicial = precioInicial;
    }

    public String getTipoCasilla(){
        return tipoCasilla;
    }

    public TipoColor getColor(){

        return color;

    }

    public int getTamano() {
        return tamano;
    }
}
