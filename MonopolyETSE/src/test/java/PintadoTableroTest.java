import aplicacion.salidaPantalla.TableroASCII;
import monopoly.Juego;
import org.junit.Before;
import org.junit.Test;

public class PintadoTableroTest {

    private Juego juego;

    @Before
    public void setUp()
    {
        juego = new Juego();
    }

    @Test
    public void imprimirTablero()
    {
        TableroASCII.pintaTablero(juego.getTablero(), 1);
    }


    /*@Test
    public void imprimirTablero()
    {
        System.out.println(TableroASCII.pintaTablero(null, 1));
    }*/

}
