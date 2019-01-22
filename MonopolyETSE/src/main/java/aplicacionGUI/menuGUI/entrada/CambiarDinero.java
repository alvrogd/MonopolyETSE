package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.editor.Celda;
import aplicacionGUI.input.ILectorEntero;
import aplicacionGUI.input.InputEntero;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.jugadores.tratos.Trato;

public abstract class CambiarDinero implements ILectorEntero {

    // Trato del que se desea cambiar el dinero a recibir
    private Trato trato;
    private MenuGUI menuGUI;
    private InputEntero inputEntero;

    /* Constructor */

    /**
     * Se crea un handler para el cambio del dinero introducido en el trato a recibir por el jugador
     */
    public CambiarDinero(Trato trato, MenuGUI menuGUI) {
        if(trato == null){
            System.err.println("Trato no inicializado");
            System.exit(1);
        }

        if(menuGUI == null){
            System.err.println("Menú no inicializado");
            System.exit(1);
        }

        this.menuGUI = menuGUI;
        this.trato = trato;
        this.inputEntero = new InputEntero(false, 0, this);
    }

    public Trato getTrato() {
        return trato;
    }

    public void setTrato(Trato trato) {
        this.trato = trato;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public void setMenuGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
    }

    public InputEntero getInputEntero() {
        return inputEntero;
    }

    public void setInputEntero(InputEntero inputEntero) {
        this.inputEntero = inputEntero;
    }
}
