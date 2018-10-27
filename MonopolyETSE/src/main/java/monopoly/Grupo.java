package monopoly;


import java.util.ArrayList;
import java.util.Objects;

public class Grupo {

    private final TipoGrupo tipo;
    private final double precio;
    private final ArrayList<Casilla> casillas;

    public Grupo(TipoGrupo tipo, Jugador Banca, String... casillas) {

        //Comprobación del tipo de grupo
        if (tipo == null) {
            System.out.println("Tipo referencia a null");
            System.exit(1);
        }
        if (Banca == null) {
            System.out.println("Banca referencia a null");
            System.exit(1);
        }

        this.tipo = tipo;
        precio = tipo.getPrecioInicial();

        //Comprobación de que las casillas no son null
        this.casillas = new ArrayList<>();
        Casilla aux;
        for (String c : casillas) {
            if (c == null) {
                System.out.println("Casilla incorrecta.");
                System.exit(1);
            }
            aux = new Casilla(c, this, Banca);
            this.casillas.add(aux);
        }

    }

    public double getPrecio() {
        return precio;
    }

    public TipoGrupo getTipo() {
        return tipo;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof Grupo)) {
            return false;
        } else if (this == o) {
            return true;
        } else if (tipo.equals(((Grupo) o).tipo)) {
            return true;
        }
        return false;
    }

}
