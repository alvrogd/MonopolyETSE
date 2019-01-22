package aplicacionGUI.ejecucionJuego.handlers;

import aplicacionGUI.ejecucionJuego.EjecucionJuego;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final EjecucionJuego ejecucionJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para una ejecución del Monopoly
     *
     * @param ejecucionJuego ejecución del juego asociada
     */
    public Pulsacion(EjecucionJuego ejecucionJuego) {

        if (ejecucionJuego == null) {
            System.err.println("Ejecución del juego no inicializada");
            System.exit(1);
        }

        this.ejecucionJuego = ejecucionJuego;
    }



    /* Getters y setters */

    public EjecucionJuego getEjecucionJuego() {
        return ejecucionJuego;
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
        getEjecucionJuego().setxPresionado(x);
        getEjecucionJuego().setyPresionado(y);

        // Se ocultan todos los menús contextuales abiertos
        for (ContextMenu contextMenu : getEjecucionJuego().getMenus()) {
            contextMenu.hide();
        }

        // Y se eliminan
        getEjecucionJuego().getMenus().clear();

        // Si se ha pulsado la sección de controles
        if (getEjecucionJuego().getMenuGUI().contienePosicion(x, y)) {
            getEjecucionJuego().getMenuGUI().handleClickPulsado(x, y);
        }

        final ArrayList<Input> inputsActivos = getEjecucionJuego().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido ha sido pulsado
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handlePulsacion();
            }
        }
    }
}
