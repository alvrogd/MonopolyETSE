package monopoly.tablero.jerarquiaCasillas;

import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.Participante;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class Transporte extends Propiedad {

    public Transporte(String nombre, Grupo grupo, boolean comprable, int posicion, Participante propietario, Tablero tablero){
        super(nombre, grupo, comprable, posicion, propietario, tablero);
        setPrecioInicial(grupo.getPrecio());
    }

    @Override
    public int getPrecioActual(){
        return(getGrupo().getPrecio());
    }

    @Override
    public void actualizarAlquiler(){

        int importe = (int)(getImporteCompra());

        if(importe == 0)
            importe = (int)(getPrecioActual());

        int numTransportes = getPropietario().numeroCasillasObtenidas(TipoGrupo.transporte);

        //Se realizan comprobaciones para que en caso de que aún no se haya comprado la propiedad, el alquiler sea igual
        //al supuesto alquiler en caso de que estuviese comprada.

        if(numTransportes == 0)
            numTransportes++;

        setAlquiler((int)((numTransportes/4.0)* importe));

    }

    @Override
    public String toString(){

        String salida = super.toString();

        salida += "        -> Alquiler con 1 transporte:        " + (getPrecioActual() * 0.25) + "K €" + "\n";
        salida += "        -> Alquiler con 2 transportes:       " + (getPrecioActual() * 0.5) + "K €" + "\n";
        salida += "        -> Alquiler con 3 transportes:       " + (getPrecioActual() * 0.75) + "K €" + "\n";
        salida += "        -> Alquiler con 4 transportes:       " + (getPrecioActual()) + "K €" + "\n";

        return salida;
    }
}
