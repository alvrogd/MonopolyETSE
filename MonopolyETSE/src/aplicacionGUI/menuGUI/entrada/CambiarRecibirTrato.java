package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Trato;

public class CambiarRecibirTrato extends CambiarTrato {

    public CambiarRecibirTrato(Trato trato, MenuGUI menuGUI) {
        super(trato, menuGUI, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN_OSCURA);
    }

    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {
        getTrato().setDineroRecibir(enteroLeido);

        getInputEntero().eliminarRastro();
        // Como ya se ha cumplido la funcionalidad se le puede indicar al menú que puede continuar el siguiente paso
        getMenuGUI().setSiguientePaso(true);

    }
}
