package aplicacionGUI.ejecucionJuego.handlers;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionJuego.EjecucionJuego;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClickIzquierdo implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final EjecucionJuego ejecucionJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de click izquierdo para una ejecución del Monopoly
     *
     * @param ejecucionJuego ejecución del juego asociada
     */
    public ClickIzquierdo(EjecucionJuego ejecucionJuego) {

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
     * Se actúa ejecutando las acciones predefinidas ante un click izquierdo
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones del click
        double x = e.getX();
        double y = e.getY();

        // Si se ha pulsado la sección de controles
        if (getEjecucionJuego().getMenuGUI().contienePosicion(x, y)) {

            // Se actúa en caso de que el botón presionado sea el primario (izquierdo)
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                getEjecucionJuego().getMenuGUI().handleClickIzquierdo(x, y);
            }
        }

        // Si la sección de información ha sido pulsada
        else if (getEjecucionJuego().getInformacion().contienePosicion(x, y)) {

            // Si se ha pulsado el botón primario
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                // Se comprueba que no haya ningún Input activo
                if(!getEjecucionJuego().getMenuGUI().isInputActivo()) {
                    getEjecucionJuego().getInformacion().handleClickIzquierdo(x, y);
                }
            }

            // Si se ha pulsado el botón secundario
            else if (e.getButton().equals(MouseButton.SECONDARY)) {
                getEjecucionJuego().getInformacion().handleClickDerecho(x, y, getEjecucionJuego().getRaiz(), e,
                        getEjecucionJuego().getMenus(), getEjecucionJuego().getApp());
            }
        }
    }
}
