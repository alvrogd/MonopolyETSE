import aplicacion.salidaPantalla.TableroASCII;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import org.junit.Before;
import org.junit.Test;

public class PintadoTableroTest {

    private Juego juego;


   /* public void setUp()
    {
        juego = new Juego();
        juego.addJugador(new Jugador( "Pepe", juego.getTablero(), TipoAvatar.coche,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.addJugador(new Jugador( "Paco", juego.getTablero(), TipoAvatar.coche,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.addJugador(new Jugador( "Yisucristo", juego.getTablero(), TipoAvatar.coche,
                juego.getTablero().getCasillas().get(0).get(0)));
        juego.getTablero().getCasillas().get(1).get(1).setPropietario(juego.getJugadores().get("Pepe"));
    }

    @Test
    public void imprimirTablero()
    {
        System.out.println(TableroASCII.pintaTablero(juego.getTablero(), null));
    }


    /*@Test
    public void imprimirTablero()
    {
        System.out.println(TableroASCII.pintaTablero(null, 1));
    }*/

}
