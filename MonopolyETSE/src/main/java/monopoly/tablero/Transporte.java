package monopoly.tablero;

import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class Transporte extends Propiedad {

    public Transporte(String nombre, Grupo grupo, boolean comprable, int posicion, Jugador propietario, Tablero tablero){
        super(nombre, grupo, comprable, posicion, propietario, tablero);
    }

    @Override
    public void actualizarAlquiler(){

        int importe = (int)(Constantes.COEF_ALQUILER * getImporteCompra());

        if(importe == 0)
            importe = (int)(Constantes.COEF_ALQUILER * getPrecioActual());

        int numTransportes = getPropietario().numeroCasillasObtenidas(TipoGrupo.transporte);

        //Se realizan comprobaciones para que en caso de que aún no se haya comprado la propiedad, el alquiler sea igual
        //al supuesto alquiler en caso de que estuviese comprada.

        if(numTransportes == 0)
            numTransportes++;

        setAlquiler((int)((numTransportes/4.0)* importe));

    }
}
