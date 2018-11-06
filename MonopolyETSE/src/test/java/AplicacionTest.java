import aplicacion.TipoComando;
import aplicacion.salidaPantalla.TableroASCII;
import aplicacion.Aplicacion;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

import org.junit.Test;

import java.util.ArrayList;

public class AplicacionTest {

    @Test
    public void crearAplicacion() {

        Aplicacion aplicacion = new Aplicacion();

        aplicacion.getJuego().addJugador(new Jugador("Fran", aplicacion.getJuego().getTablero(), TipoAvatar.coche,
                aplicacion.getJuego().getTablero().getCasillas().get(0).get(0)));
        aplicacion.getJuego().addJugador(new Jugador("Alvaro", aplicacion.getJuego().getTablero(), TipoAvatar.coche,
                aplicacion.getJuego().getTablero().getCasillas().get(0).get(0)));
        aplicacion.getJuego().addJugador(new Jugador("Perico", aplicacion.getJuego().getTablero(), TipoAvatar.esfinge,
                aplicacion.getJuego().getTablero().getCasillas().get(0).get(0)));

        aplicacion.getJuego().iniciarJuego();

        aplicacion.introducirComando("ver tablero");
    }

}
