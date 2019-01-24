package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.input.ILectorEntero;
import aplicacionGUI.input.InputEntero;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Trato;

public abstract class CambiarTrato implements ILectorEntero {

    // Trato del que se desea cambiar el dinero a recibir
    private Trato trato;
    private MenuGUI menuGUI;
    private InputEntero inputEntero;

    /* Constructor */

    /**
     * Se crea un handler para el cambio del dinero introducido en el trato a recibir por el jugador
     */
    public CambiarTrato(Trato trato, MenuGUI menuGUI, String imagen, String imagenOscura) {
        if(trato == null){
            System.err.println("Trato no inicializado");
            System.exit(1);
        }

        if(menuGUI == null){
            System.err.println("Menú no inicializado");
            System.exit(1);
        }

        this.menuGUI = menuGUI;
        menuGUI.setInputActivo(true);
        this.trato = trato;
        this.inputEntero = new InputEntero(false, 0, this, imagen, imagenOscura);
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
