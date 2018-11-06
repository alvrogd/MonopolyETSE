package monopoly.tablero;

import aplicacion.salidaPantalla.TipoColor;
import monopoly.Constantes;

public enum TipoGrupo {

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
    carcel("carcel",TipoColor.resetAnsi),
    parking("parking",0, TipoColor.resetAnsi),
    salida("salida",TipoColor.resetAnsi),
    irCarcel("ir a la cárcel",TipoColor.resetAnsi);

    private final int precioInicial;
    private final TipoColor color;
    private final String tipoCasilla;

    private TipoGrupo(String tipocasilla,TipoColor color){
        this.precioInicial = -1;
        this.color = color;
        this.tipoCasilla = tipocasilla;
    }

    private TipoGrupo(String tipocasilla, int precioInicial, TipoColor color) {

        this.tipoCasilla = tipocasilla;
        this.precioInicial = precioInicial;
        this.color = color;

    }

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
