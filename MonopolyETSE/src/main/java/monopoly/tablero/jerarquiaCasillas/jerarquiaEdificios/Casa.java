package monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios;

import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class Casa extends Edificio{

    public Casa(Solar posicion, TipoGrupo grupo){

        super(posicion, TipoEdificio.casa, grupo);

    }

}
