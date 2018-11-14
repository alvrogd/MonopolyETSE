import aplicacion.TipoComando;
import aplicacion.salidaPantalla.TableroASCII;
import aplicacion.Aplicacion;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionTest {

    @Test
    public void crearAplicacion() {

        Scanner entrada = new Scanner(System.in);
        Aplicacion aplicacion = new Aplicacion();

        aplicacion.getJuego().addJugador(new Jugador("Fran", aplicacion.getJuego().getTablero(), TipoAvatar.coche,
                aplicacion.getJuego().getTablero().getCasillas().get(0).get(0)));
        aplicacion.getJuego().addJugador(new Jugador("Alvaro", aplicacion.getJuego().getTablero(), TipoAvatar.coche,
                aplicacion.getJuego().getTablero().getCasillas().get(0).get(0)));

        aplicacion.getJuego().iniciarJuego();
        aplicacion.getJuego().getJugadores().get("Fran").setFortuna(1000);

        //System.out.println(TableroASCII.pintaTablero(aplicacion.getJuego().getTablero()));

            System.out.println("Mover: ");

            //aplicacion.getJuego().getTurno().getAvatar().mover(2, true);
        aplicacion.introducirComando("describir cyan3");
        aplicacion.getJuego().getTurno().getAvatar().mover(18, true);

        aplicacion.introducirComando("describir jugador Fran");



    }

}
