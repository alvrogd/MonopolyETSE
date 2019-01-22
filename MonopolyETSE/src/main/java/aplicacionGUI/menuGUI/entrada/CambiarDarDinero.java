package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.input.Input;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Trato;

public class CambiarDarDinero extends CambiarDinero{

    public CambiarDarDinero(Trato trato, MenuGUI menuGUI) {
        super(trato, menuGUI);
    }

    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {
        // Como ya se ha cumplido la funcionalidad se le puede indicar al menú que puede continuar el siguiente paso
        getTrato().setDineroDar(enteroLeido);

        // Elimina su nodo
        Input.getRaiz().getChildren().remove(getInputEntero().getNodo());

        // Se elimina de la lista de inputs activos
        Input.getInputsActivos().clear();
        getMenuGUI().setSiguientePaso(true);
    }
}
