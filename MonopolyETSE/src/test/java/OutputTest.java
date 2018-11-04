import aplicacion.Aplicacion;
import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import org.junit.Before;
import org.junit.Test;

public class OutputTest {

    private Juego juego;

    @Before
    public void setUp()
    {
        juego = new Juego();
    }

    @Test
    public void imprimirTablero()
    {
        Jugador jugador = new Jugador("Paquita la Chocolatera", juego.getTablero(), TipoAvatar.coche, juego.getTablero().getCasillas().get(0).get(0));
        jugador.setFortuna(1000);
        System.out.println(jugador.getNombre());
        System.out.println(jugador.getFortuna());

        Output.imprimirCabeceraJugador(jugador);
        Output.imprimirEntradaComando();

        Aplicacion app = new Aplicacion();

        app.introducirComando(jugador);
    }

}
