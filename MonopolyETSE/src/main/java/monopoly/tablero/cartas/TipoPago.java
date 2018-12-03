package monopoly.tablero.cartas;

import java.util.ArrayList;
import java.util.Arrays;

public enum TipoPago {

    pagarMatriculaColegio( false, 1500, "banca", "Paga 1500K€ por la matrícula de Hogwarts." ),
    pagarBienesInmuebles( true, 0, "banca", "La guerra de los cinco reyes está empobreciendo al reinado, por lo que se ",
            "incrementan los impuestos sobre  bienes  inmuebles  afecta  a  todas  tus  propiedades.  Paga  400K€  por ",
            "casa, 1150k€ por hotel, 200K€ por piscina y 750K€ por pista de deportes." ),
    pagarPresidente( false, 150, "jugadores", "Has ganado la guerra y te has hecho con el trono de hierro.", "Paga a cada jugador 250K€." ),
    pagarMovil( false, 1500, "banca", "Te multan por hacer magia fuera del colegio. Paga 1500K€" ),
    pagarBalneario( false, 500, "banca", "Te vas de balneario para intentar abrir el huevo de dragón. Paga 500K€." ),
    pagarViajeLeon( false, 1000, "banca", "Invitas a tus amigos a un viaje intergaláctico. Paga 1000K€." ),
    pagarAlquilerCannes( false, 200, "jugadores", "Alquilas a tus compañeros naves espaciales. Paga 200K€ a cada jugador." );


    /* Atributos */

    // Si el importe a pagar es directo o debe calcularse
    private final boolean importeCalculado;

    // Importe en caso de que sea directo
    private final int importe;

    // Jugador(es) receptor(es)
    private final String nombreReceptor;

    // Descripción de la carta
    private final String[] descripcion;


    /* Constructor */

    TipoPago(boolean importeCalculado, int importe, String nombreReceptor, String... descripcion ) {

        this.importeCalculado = importeCalculado;
        this.importe = importe;
        this.nombreReceptor = nombreReceptor;
        this.descripcion = descripcion;

    }


    /* Métodos */

    public boolean isImporteCalculado() {
        return importeCalculado;
    }

    public int getImporte() {
        return importe;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public ArrayList<String> getDescripcion() {

        ArrayList<String> des = new ArrayList<>(Arrays.asList(this.descripcion));

        return des;
    }

}
