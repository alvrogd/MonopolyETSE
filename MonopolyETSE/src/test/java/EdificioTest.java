
import monopoly.Edificio;
import monopoly.TipoEdificio;
import monopoly.TipoGrupo;
import org.junit.Test;

public class EdificioTest {

    @Test
    public void constructorEdificio(){
        Edificio casa = new Edificio(TipoEdificio.casa, TipoGrupo.naranja);
        System.out.println("s");

    }

}
