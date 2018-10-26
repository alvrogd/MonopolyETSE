package monopoly;


import java.util.ArrayList;
import java.util.Objects;

public class Grupo {

    private final TipoGrupo tipo;
    private final double precio;
    private final ArrayList<Casilla> casillas;

    public Grupo(TipoGrupo tipo, Casilla... casillas) {

        //Comprobación del tipo de grupo
        switch(tipo) {
            case negro:
                precio = Constantes.PRECIO_INICIAL_GRUPO_0;
                this.tipo = tipo;
                break;
            case cyan:
                precio = Constantes.PRECIO_INICIAL_GRUPO_1;
                this.tipo = tipo;
                break;
            case rosa:
                precio = Constantes.PRECIO_INICIAL_GRUPO_2;
                this.tipo = tipo;
                break;
            case naranja:
                precio = Constantes.PRECIO_INICIAL_GRUPO_3;
                this.tipo = tipo;
                break;
            case rojo:
                precio = Constantes.PRECIO_INICIAL_GRUPO_4;
                this.tipo = tipo;
                break;
            case marron:
                precio = Constantes.PRECIO_INICIAL_GRUPO_5;
                this.tipo = tipo;
                break;
            case verde:
                precio = Constantes.PRECIO_INICIAL_GRUPO_6;
                this.tipo = tipo;
                break;
            case azul:
                precio = Constantes.PRECIO_INICIAL_GRUPO_7;
                this.tipo = tipo;
                break;
            default:
                precio = 0;
                this.tipo = null;
                System.out.println("No se ha especificado el grupo.");
                System.exit(1);
        }

        //Comprobación de que las casillas no son null
        this.casillas = new ArrayList<>();
        for (Casilla c : casillas) {
            if (c == null) {
                System.out.println("Casilla no inicializada.");
                System.exit(1);
            }
            this.casillas.add(c);
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
        if(o == null){
            return false;
        } else if (!(o instanceof Grupo)) {
            return false;
        } else if (this == o) {
            return true;
        } else if (tipo.equals(((Grupo) o).tipo)){
            return true;
        }
        return false;
    }

}
