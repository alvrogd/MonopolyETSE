package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.input.Input;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Trato;

public class CambiarRecibirDinero extends CambiarDinero{

    public CambiarRecibirDinero(Trato trato, MenuGUI menuGUI) {
        super(trato, menuGUI);
    }

    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {
        getTrato().setDineroRecibir(enteroLeido);

        // Como ya se ha cumplido la funcionalidad se le puede indicar al menú que puede continuar el siguiente paso
        getMenuGUI().setSiguientePaso(true);
    }
}
