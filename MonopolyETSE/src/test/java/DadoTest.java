import monopoly.Dado;
import org.junit.*;
import static junit.framework.TestCase.*;

public class DadoTest {

    private Dado dado;

    @Before
    public void setUp()
    {
        dado = new Dado();
    }

    @Test
    public void veinteTiradas()
    {
        for( int i = 0; i < 20; i++ )
            System.out.println( dado.lanzarDado() );
    }

}
