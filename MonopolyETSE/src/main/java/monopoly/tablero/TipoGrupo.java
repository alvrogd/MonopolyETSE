package monopoly.tablero;

import aplicacion.salidaPantalla.TipoColor;
import monopoly.Constantes;

public enum TipoGrupo {

    /*Tipos de enumeraciones*/
    negro("solar",Constantes.PRECIO_INICIAL_GRUPO_0, TipoColor.negroANSI),
    cyan("solar",Constantes.PRECIO_INICIAL_GRUPO_1, TipoColor.cianANSI),
    rosa("solar",Constantes.PRECIO_INICIAL_GRUPO_2, TipoColor.violetaANSI),
    naranja("solar",Constantes.PRECIO_INICIAL_GRUPO_3, TipoColor.amarilloANSI),
    rojo("solar",Constantes.PRECIO_INICIAL_GRUPO_4, TipoColor.rojoANSI),
    marron("solar",Constantes.PRECIO_INICIAL_GRUPO_5, TipoColor.blancoANSI),
    verde("solar",Constantes.PRECIO_INICIAL_GRUPO_6, TipoColor.verdeANSI),
    azul("solar",Constantes.PRECIO_INICIAL_GRUPO_7, TipoColor.azulANSI),
    suerte("suerte",TipoColor.resetAnsi),
    comunidad("comunidad",TipoColor.resetAnsi),
    impuesto1("impuesto",Constantes.IMPUESTO_1, TipoColor.resetAnsi),
    impuesto2("impuesto",Constantes.IMPUESTO_2, TipoColor.resetAnsi),
    transporte("transporte",Constantes.DINERO_TRANSPORTES, TipoColor.resetAnsi),
    servicios("servicio",Constantes.DINERO_SERVICIOS, TipoColor.resetAnsi),
    carcel("carcel",Constantes.DINERO_CARCEL,TipoColor.resetAnsi),
    parking("parking",0, TipoColor.resetAnsi),
    salida("salida",Constantes.DINERO_SALIDA,TipoColor.resetAnsi),
    irCarcel("ir a la cárcel",TipoColor.resetAnsi);

    /*Atributos*/

    //Precio inicial del grupo
    private final int precioInicial;

    //El color con el que se imprimirá el grupo
    private final TipoColor color;

    //String con el tipo de casilla que llevará.
    private final String tipoCasilla;

    /**
     * Constructor paa grupos que no tengan un importe relacionado, el cual se establece a -10
     * @param tipocasilla se establece el tipo de casilla
     * @param color el color del grupo
     */
    private TipoGrupo(String tipocasilla,TipoColor color){
        this.precioInicial = -10;
        this.color = color;
        this.tipoCasilla = tipocasilla;
    }

    /**
     * Constructor para grupos que tengan un importe relacionado
     * @param tipocasilla se establece el tipo de casilla
     * @param precioInicial importe a establecer para el grupo
     * @param color color del grupo
     */
    private TipoGrupo(String tipocasilla, int precioInicial, TipoColor color) {

        this.tipoCasilla = tipocasilla;
        this.precioInicial = precioInicial;
        this.color = color;

    }

    /*Getters y Setters*/
    public int getPrecioInicial() {

        return precioInicial;

    }

    public String getTipoCasilla(){
        return tipoCasilla;
    }

    public TipoColor getColor(){

        return color;

    }

}
