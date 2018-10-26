package monopoly;

public class Edificio {

    private final TipoEdificio tipo;
    private double precioCompra;

    //Constructor --> se pasa el tipo de edificio y el tipo de grupo para determinar su precio de compra.

    public Edificio(TipoEdificio tipo, TipoGrupo grupo) {

        //Comprobación de TipoGrupo
        if(tipo == null) {
            System.out.println("Error en el tipo de edificio.");
            System.exit(1);
        }

        this.tipo = tipo;

        switch (grupo) {
            case negro:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_0;
                break;
            case cyan:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_1;
                break;
            case rosa:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_2;
                break;
            case naranja:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_3;
                break;
            case rojo:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_4;
                break;
            case marron:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_5;
                break;
            case verde:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_6;
                break;
            case azul:
                precioCompra = Constantes.PRECIO_INICIAL_GRUPO_7;
                break;
            default:
                System.out.println("Error en el tipo de grupo.");
                System.exit(1);
        }

        //Comprobación de TipoEdificio

        switch (tipo) {
            case casa:
                precioCompra *= Constantes.COEF_CASA;
                break;
            case hotel:
                precioCompra *= Constantes.COEF_HOTEL;
                break;
            case piscina:
                precioCompra *= Constantes.COEF_PISCINA;
                break;
            case pistaDeporte:
                precioCompra *= Constantes.COEF_PISTADEPORTE;
                break;
            default:
                System.exit(1);

        }
    }


    public TipoEdificio getTipo() {
        return tipo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

}
