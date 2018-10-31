package monopoly.tablero;

import monopoly.Constantes;

public enum TipoGrupo {

    negro(Constantes.PRECIO_INICIAL_GRUPO_0),
    cyan(Constantes.PRECIO_INICIAL_GRUPO_1),
    rosa(Constantes.PRECIO_INICIAL_GRUPO_2),
    naranja(Constantes.PRECIO_INICIAL_GRUPO_3),
    rojo(Constantes.PRECIO_INICIAL_GRUPO_4),
    marron(Constantes.PRECIO_INICIAL_GRUPO_5),
    verde(Constantes.PRECIO_INICIAL_GRUPO_6),
    azul(Constantes.PRECIO_INICIAL_GRUPO_7),
    suerte(),
    comunidad(),
    impuesto1(Constantes.IMPUESTO_1),
    impuesto2(Constantes.IMPUESTO_2),
    transporte(),
    servicios(),
    carcel(),
    parking(),
    salida(),
    irCarcel();

    private final double precioInicial;

    private TipoGrupo(){
        this.precioInicial = -1;
    }

    private TipoGrupo(double precioInicial) {

        this.precioInicial = precioInicial;

    }

    public double getPrecioInicial() {

        return precioInicial;

    }

}
