package aplicacionGUI.ejecucionAplicacion.fases.faseEditor.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.FaseEditor;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del editor asociada
    private final FaseEditor faseEditor;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de release del ratón para una ejecución del editor
     *
     * @param faseEditor ejecución del juego asociada
     */
    public Release(FaseEditor faseEditor) {

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
     * Se actúa ejecutando las acciones predefinidas ante un release del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones de la pulsación anterior al release (para conocer la posición donde se comenzó a presionar el
        // botón)
        double x = getFaseEditor().getxPresionado();
        double y = getFaseEditor().getyPresionado();

        // Si se ha pulsado el editor
        if(getFaseEditor().getEditor().contienePosicion(x, y)){
            getFaseEditor().getEditor().handleClick(x, y, getFaseEditor().getRaiz(), e,
                    getFaseEditor().getMenus());
        }

        final ArrayList<Input> inputsActivos = getFaseEditor().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido contiene la posición en la cual se pulsó el botón del ratón
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handleRelease();
            }
        }
    }
}
