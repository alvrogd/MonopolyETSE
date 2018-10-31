package monopoly.tablero;

import monopoly.Constantes;

public enum TipoEdificio {
    casa(Constantes.COEF_CASA),
    hotel(Constantes.COEF_HOTEL),
    piscina(Constantes.COEF_PISCINA),
    pistaDeporte(Constantes.COEF_PISTADEPORTE);

    private final double compra;

    private TipoEdificio(double compra) {

        this.compra = compra;

    }

    public double getCompra() {

        return compra;

    }
}
