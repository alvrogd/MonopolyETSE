package aplicacionGUI.ejecucionEditor.handlers;

import aplicacionGUI.ejecucionEditor.EjecucionEditor;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del editor asociada
    private final EjecucionEditor ejecucionEditor;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de release del ratón para una ejecución del editor
     *
     * @param ejecucionEditor ejecución del juego asociada
     */
    public Release(EjecucionEditor ejecucionEditor) {

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
     * Se actúa ejecutando las acciones predefinidas ante un release del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones de la pulsación anterior al release (para conocer la posición donde se comenzó a presionar el
        // botón)
        double x = getEjecucionEditor().getxPresionado();
        double y = getEjecucionEditor().getyPresionado();

        // Si se ha pulsado el editor
        if(getEjecucionEditor().getEditor().contienePosicion(x, y)){
            getEjecucionEditor().getEditor().handleClick(x, y, getEjecucionEditor().getRaiz(), e,
                    getEjecucionEditor().getMenus());
        }

        final ArrayList<Input> inputsActivos = getEjecucionEditor().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido contiene la posición en la cual se pulsó el botón del ratón
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handleRelease();
            }
        }
    }
}
