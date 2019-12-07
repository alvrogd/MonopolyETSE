package monopoly.tablero.jerarquiaCasillas.jerarquiaAccion;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Tablero;

public class IrCarcel extends Especial{

    public IrCarcel(String nombre, int posicion, Tablero tablero){

        super(nombre, posicion, tablero);

    }

    public void ejecutarAccion(Jugador jugador){

        if(jugador == null){
            System.err.println("Jugador referencia a null");
            System.exit(-1);
        }

        Avatar avatar = jugador.getAvatar();

        avatar.getPosicion().getAvataresContenidos().remove(avatar.getIdentificador());

        jugador.incrementarVecesEnLaCarcel(1);

        avatar.setPosicion(getTablero().getCasillas().get(Constantes.POSICION_CARCEL / 10).get(Constantes.POSICION_CARCEL % 10));
        avatar.sethaMovidoCasillasTirada(true);
        getTablero().getJuego().setHaLanzadoDados(true);
        avatar.setCasillasRestantesPorMoverse(0);
        avatar.setEncarcelado(true);
        avatar.setHaEstadoCarcel(true);

        // Y se añade el avatar al listado de avatares contenidos en la cárcel
        avatar.getPosicion().getAvataresContenidos().put(avatar.getIdentificador(), avatar);

        Output.mensaje("¡Has sido encarcelado!");

    }

}
