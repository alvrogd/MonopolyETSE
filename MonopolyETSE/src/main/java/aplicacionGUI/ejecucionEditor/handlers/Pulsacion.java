package aplicacionGUI.ejecucionEditor.handlers;

import aplicacionGUI.ejecucionEditor.EjecucionEditor;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del editor asociada
    private final EjecucionEditor ejecucionEditor;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para una ejecución del editor
     *
     * @param ejecucionEditor ejecución del juego asociada
     */
    public Pulsacion(EjecucionEditor ejecucionEditor) {

        if (ejecucionEditor == null) {
            System.err.println("Ejecución del editor no inicializada");
            System.exit(1);
        }

        this.ejecucionEditor = ejecucionEditor;
    }



    /* Getters y setters */

    public EjecucionEditor getEjecucionEditor() {
        return ejecucionEditor;
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
        getEjecucionEditor().setxPresionado(x);
        getEjecucionEditor().setyPresionado(y);

        // Se ocultan todos los menús contextuales abiertos
        for (ContextMenu contextMenu : getEjecucionEditor().getMenus()) {
            contextMenu.hide();
        }

        // Y se eliminan
        getEjecucionEditor().getMenus().clear();

        final ArrayList<Input> inputsActivos = getEjecucionEditor().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido ha sido pulsado
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handlePulsacion();
            }
        }
    }
}
