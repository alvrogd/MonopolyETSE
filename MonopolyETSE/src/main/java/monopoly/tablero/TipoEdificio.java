package monopoly.tablero;

import monopoly.Constantes;

public enum TipoEdificio {

    /*Tipo de enumeración*/
    casa("casa", "casas","   ",Constantes.COEF_CASA),
    hotel("hotel", "hoteles"," ", Constantes.COEF_HOTEL),
    piscina("piscina", "piscinas", "", Constantes.COEF_PISCINA),
    pistaDeporte("pista", "pistas", "  ",Constantes.COEF_PISTADEPORTE);

    /*Atributos*/
    private final double compra;
    private final String nombre;
    private final String plural;
    private final String espacios;

    /*Constructores*/
    private TipoEdificio(String nombre, String plural, String espacios, double compra) {

        this.compra = compra;
        this.nombre = nombre;
        this.plural = plural;
        this.espacios = espacios;

    }

    /*Métodos*/
    public double getCompra() {

        return compra;

    }

    public String getPlural() {
        return plural;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspacios() {
        return espacios;
    }
}
