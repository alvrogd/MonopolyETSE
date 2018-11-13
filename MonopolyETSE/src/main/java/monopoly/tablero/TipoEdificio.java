package monopoly.tablero;

import monopoly.Constantes;

public enum TipoEdificio {

    /*Tipo de enumeración*/
    casa(Constantes.COEF_CASA),
    hotel(Constantes.COEF_HOTEL),
    piscina(Constantes.COEF_PISCINA),
    pistaDeporte(Constantes.COEF_PISTADEPORTE);

    /*Atributos*/
    private final double compra;

    /*Constructores*/
    private TipoEdificio(double compra) {

        this.compra = compra;

    }

    /*Métodos*/
    public double getCompra() {

        return compra;

    }
}
