

import aplicacion.salidaPantalla.TableroASCII;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

import org.junit.Test;

import java.util.ArrayList;

public class JuegoTest {

    @Test
    public void crearJuego() {

        Juego juego = new Juego();

        juego.addJugador(new Jugador("Fran", juego.getTablero(), TipoAvatar.coche,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.addJugador(new Jugador("Alvaro", juego.getTablero(), TipoAvatar.coche,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.addJugador(new Jugador("Perico", juego.getTablero(), TipoAvatar.esfinge,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.iniciarJuego();

        System.out.println(TableroASCII.pintaTablero(juego.getTablero(), null));
    }

}
