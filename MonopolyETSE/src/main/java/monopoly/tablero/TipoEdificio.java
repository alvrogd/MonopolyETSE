package monopoly.tablero;

import monopoly.Constantes;

public enum TipoEdificio {

    /*Tipo de enumeración*/
    casa("casa", Constantes.COEF_CASA),
    hotel("hotel", Constantes.COEF_HOTEL),
    piscina("piscina", Constantes.COEF_PISCINA),
    pistaDeporte("pista", Constantes.COEF_PISTADEPORTE);

    /*Atributos*/
    private final double compra;
    private final String nombre;

    /*Constructores*/
    private TipoEdificio(String nombre, double compra) {

        this.compra = compra;
        this.nombre = nombre;

    }

    /*Métodos*/
    public double getCompra() {

        return compra;

    }

    public String getNombre() {
        return nombre;
    }
}
