import monopoly.Edificio;
import monopoly.TipoEdificio;
import monopoly.TipoGrupo;
import org.junit.Test;

public class EdificioTest {

    @Test
    public void constructorEdificio(){
        Edificio casa = new Edificio(TipoEdificio.pistaDeporte, TipoGrupo.negro);
        System.out.printf("Precio edificio: %.0fK\n", casa.getPrecioCompra());
        System.out.println("Tipo edificio: " + casa.getTipo());
    }

}
