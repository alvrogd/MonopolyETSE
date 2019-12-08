package monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios;

import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class Hotel extends Edificio{

    public Hotel(Solar posicion, TipoGrupo grupo){

        super(posicion, TipoEdificio.hotel, grupo);

    }

}
