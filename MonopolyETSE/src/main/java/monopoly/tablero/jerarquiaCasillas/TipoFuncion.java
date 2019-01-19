package monopoly.tablero.jerarquiaCasillas;

import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

public enum TipoFuncion {

    describir("describir"),
    hipotecar("hipotecar"),
    comprar("comprar"),
    deshipotecar("deshipotecar"),
    vender("vender"),
    venderCasa("casa"),
    venderHotel("hotel"),
    venderPiscina("piscina"),
    venderPista("pista");

    private final String nombre;

    TipoFuncion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoFuncion toFuncion(TipoEdificio tipoEdificio){

        TipoFuncion tipoFuncion;

        switch(tipoEdificio){

            case casa:
                tipoFuncion = TipoFuncion.venderCasa;
                break;

            case hotel:
                tipoFuncion = TipoFuncion.venderHotel;
                break;

            case piscina:
                tipoFuncion = TipoFuncion.venderPiscina;
                break;

            case pistaDeporte:
                tipoFuncion = TipoFuncion.venderPista;
                break;

            default:
                tipoFuncion = null;
                break;

        }

        return tipoFuncion;

    }
}
