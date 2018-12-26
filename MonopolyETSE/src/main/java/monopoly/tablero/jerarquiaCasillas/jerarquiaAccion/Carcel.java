package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Tablero;

import java.util.Set;

public class Carcel extends Especial{

    private int dineroSalir;

    public Carcel(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);
        this.dineroSalir = Constantes.DINERO_CARCEL;

    }

    public int getDineroSalir() {
        return dineroSalir;
    }

    public void setDineroSalir(int dineroSalir) {
        this.dineroSalir = dineroSalir;
    }

    public void ejecutarAccion(Jugador jugador){
        return;
    }

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "        -> Salir: " + getDineroSalir() + "K €\n";

        StringBuilder jugadoresEncarcelados = new StringBuilder("        -> Jugadores encarcelados: {");

        Set<Character> avatares = getAvataresContenidos().keySet();

        boolean flag = false;

        for (Character avatar : avatares) {

            if (getAvataresContenidos().get(avatar).isEncarcelado()) {
                if (flag) {
                    jugadoresEncarcelados.append(" , {");
                }
                jugadoresEncarcelados.append(getAvataresContenidos().get(avatar).getJugador().getNombre());
                jugadoresEncarcelados.append(", ");
                jugadoresEncarcelados.append(getAvataresContenidos().get(avatar).getTurnosEnCarcel());
                jugadoresEncarcelados.append("}");
                flag = true;
            }

        }

        if (!flag) {
            jugadoresEncarcelados.append("no hay jugadores encarcelados :-)}");
        }

        salida += jugadoresEncarcelados.toString() + "\n";

        return salida;

    }

}
