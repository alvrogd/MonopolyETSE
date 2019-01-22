package aplicacionGUI.ejecucionAplicacion.fases.faseEditor.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.FaseEditor;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del editor asociada
    private final FaseEditor faseEditor;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para una ejecución del editor
     *
     * @param faseEditor ejecución del juego asociada
     */
    public Pulsacion(FaseEditor faseEditor) {

        if (faseEditor == null) {
            System.err.println("Ejecución del editor no inicializada");
            System.exit(1);
        }

        this.faseEditor = faseEditor;
    }



    /* Getters y setters */

    public FaseEditor getFaseEditor() {
        return faseEditor;
    }



    /* Métodos */

    /**
     * Se actúa ejecutando las acciones predefinidas ante una pulsación del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones del click
        double x = e.getX();
        double y = e.getY();

        // Se almacenan en la ejecución del juego
        getFaseEditor().setxPresionado(x);
        getFaseEditor().setyPresionado(y);

        // Se ocultan todos los menús contextuales abiertos
        for (ContextMenu contextMenu : getFaseEditor().getMenus()) {
            contextMenu.hide();
        }

        // Y se eliminan
        getFaseEditor().getMenus().clear();

        final ArrayList<Input> inputsActivos = getFaseEditor().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido ha sido pulsado
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handlePulsacion();
            }
        }
    }
}
