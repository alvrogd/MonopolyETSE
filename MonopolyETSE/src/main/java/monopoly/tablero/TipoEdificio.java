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

    public static TipoEdificio toEdificio(String edificio){

        TipoEdificio tipoEdificio = null;

        switch(edificio){

            case "casa":
                tipoEdificio = TipoEdificio.casa;
                break;

            case "casas":
                tipoEdificio = TipoEdificio.casa;
                break;

            case "Casas":
                tipoEdificio = TipoEdificio.casa;
                break;

            case "Casa":
                tipoEdificio = TipoEdificio.casa;
                break;

            case "hotel":
                tipoEdificio = TipoEdificio.hotel;
                break;
            case "Hotel":
                tipoEdificio = TipoEdificio.hotel;
                break;
            case "Hoteles":
                tipoEdificio = TipoEdificio.hotel;
                break;
            case "hoteles":
                tipoEdificio = TipoEdificio.hotel;
                break;

            case "piscinas":
                tipoEdificio = TipoEdificio.piscina;
                break;
            case "piscina":
                tipoEdificio = TipoEdificio.piscina;
                break;
            case "Piscina":
                tipoEdificio = TipoEdificio.piscina;
                break;
            case "Piscinas":
                tipoEdificio = TipoEdificio.piscina;
                break;

            case "pista":
                tipoEdificio = TipoEdificio.pistaDeporte;
                break;
            case "Pista":
                tipoEdificio = TipoEdificio.pistaDeporte;
                break;
            case "pistas":
                tipoEdificio = TipoEdificio.pistaDeporte;
                break;
            case "Pistas":
                tipoEdificio = TipoEdificio.pistaDeporte;
                break;

        }

        return tipoEdificio;

    }
}
