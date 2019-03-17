package aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.FaseJuego;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJuego faseJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para una ejecución del Monopoly
     *
     * @param faseJuego ejecución del juego asociada
     */
    public Pulsacion(FaseJuego faseJuego) {

        if (faseJuego == null) {
            System.err.println("Ejecución del juego no inicializada");
            System.exit(1);
        }

        this.faseJuego = faseJuego;
    }



    /* Getters y setters */

    public FaseJuego getFaseJuego() {
        return faseJuego;
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
        getFaseJuego().setxPresionado(x);
        getFaseJuego().setyPresionado(y);

        // Se ocultan todos los menús contextuales abiertos
        for (ContextMenu contextMenu : getFaseJuego().getMenus()) {
            contextMenu.hide();
        }

        // Y se eliminan
        getFaseJuego().getMenus().clear();

        // Si se ha pulsado la sección de controles
        if (getFaseJuego().getMenuGUI().contienePosicion(x, y)) {
            getFaseJuego().getMenuGUI().handleClickPulsado(x, y);
        }

        final ArrayList<Input> inputsActivos = getFaseJuego().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido ha sido pulsado
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handlePulsacion();
            }
        }
    }
}
