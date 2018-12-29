package monopoly.tablero.jerarquiaCasillas;

import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.Participante;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;

public class Servicio extends Propiedad{

    public Servicio(String nombre, Grupo grupo, boolean comprable, int posicion, Participante propietario, Tablero tablero){
        super(nombre, grupo, comprable, posicion, propietario, tablero);
        setPrecioInicial(grupo.getPrecio());
    }

    @Override
    public int getPrecioActual(){
        return(getGrupo().getPrecio());
    }

    @Override
    public void actualizarAlquiler(){

        int importe = (int)(Constantes.COEF_ALQUILER * getImporteCompra());

        if(importe == 0)
            importe = (int)(Constantes.COEF_ALQUILER * getPrecioActual());

        int numServicios = getPropietario().numeroCasillasObtenidas(TipoGrupo.servicios);

        if(numServicios == 0)
            numServicios++;

        switch(numServicios){
            case 1:
                setAlquiler(4*Constantes.FACTOR_SERVICIO);
                break;

            case 2:
                setAlquiler(10*Constantes.FACTOR_SERVICIO);
                break;
        }
    }
}
