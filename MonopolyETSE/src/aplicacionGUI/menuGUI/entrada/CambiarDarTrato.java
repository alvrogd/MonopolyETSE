package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Trato;

public class CambiarDarTrato extends CambiarTrato {

    public CambiarDarTrato(Trato trato, MenuGUI menuGUI) {
        super(trato, menuGUI, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN_OSCURA);
    }

    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {
        // Como ya se ha cumplido la funcionalidad se le puede indicar al menú que puede continuar el siguiente paso
        getTrato().setDineroDar(enteroLeido);

        getInputEntero().eliminarRastro();
        getMenuGUI().setSiguientePaso(true);
    }
}
