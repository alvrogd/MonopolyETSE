package monopoly;

public class Edificio {

    /* Atributos */
    private final TipoEdificio tipo;
    private final double precioCompra;


    /* Constructor */
    public Edificio(TipoEdificio tipoEdificio, TipoGrupo grupo) {

        //Comprobación de TipoGrupo
        if (tipoEdificio == null) {
            System.out.println("Error en el tipo de edificio.");
            System.exit(1);
        }

        if (grupo == null) {
            System.out.println("Error en el tipo de grupo");
            System.exit(1);
        }

        tipo = tipoEdificio;

        precioCompra = grupo.getPrecioInicial() * tipoEdificio.getCompra();

    }

    /* Getters */
    public TipoEdificio getTipo() {
        return tipo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

}
