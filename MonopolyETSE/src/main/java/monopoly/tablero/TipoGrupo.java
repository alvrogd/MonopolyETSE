package monopoly.tablero;

import aplicacion.salidaPantalla.TipoColor;
import monopoly.Constantes;

public enum TipoGrupo {

    negro(Constantes.PRECIO_INICIAL_GRUPO_0, TipoColor.negroANSI),
    cyan(Constantes.PRECIO_INICIAL_GRUPO_1, TipoColor.cianANSI),
    rosa(Constantes.PRECIO_INICIAL_GRUPO_2, TipoColor.violetaANSI),
    naranja(Constantes.PRECIO_INICIAL_GRUPO_3, TipoColor.amarilloANSI),
    rojo(Constantes.PRECIO_INICIAL_GRUPO_4, TipoColor.rojoANSI),
    marron(Constantes.PRECIO_INICIAL_GRUPO_5, TipoColor.amarilloANSI),
    verde(Constantes.PRECIO_INICIAL_GRUPO_6, TipoColor.verdeANSI),
    azul(Constantes.PRECIO_INICIAL_GRUPO_7, TipoColor.azulANSI),
    suerte(TipoColor.resetAnsi),
    comunidad(TipoColor.resetAnsi),
    impuesto1(Constantes.IMPUESTO_1, TipoColor.resetAnsi),
    impuesto2(Constantes.IMPUESTO_2, TipoColor.resetAnsi),
    transporte(TipoColor.resetAnsi),
    servicios(TipoColor.resetAnsi),
    carcel(TipoColor.resetAnsi),
    parking(0, TipoColor.resetAnsi),
    salida(TipoColor.resetAnsi),
    irCarcel(TipoColor.resetAnsi);

    private final int precioInicial;
    private final TipoColor color;

    private TipoGrupo(TipoColor color){
        this.precioInicial = -1;
        this.color = color;
    }

    private TipoGrupo(int precioInicial, TipoColor color) {

        this.precioInicial = precioInicial;
        this.color = color;

    }

    public int getPrecioInicial() {

        return precioInicial;

    }

    public TipoColor getColor(){

        return color;

    }

}
