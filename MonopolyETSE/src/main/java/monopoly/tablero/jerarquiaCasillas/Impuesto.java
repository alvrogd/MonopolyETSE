package monopoly.tablero.jerarquiaCasillas;

import monopoly.tablero.Tablero;

public class Impuesto extends Casilla {

    private final int impuesto;

    public Impuesto(String nombre, int posicion, Tablero tablero, int impuesto){

        super(nombre, posicion, tablero);

        this.impuesto = impuesto;
    }

    public int getImpuesto() {
        return impuesto;
    }
}
