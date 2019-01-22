package aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.FaseJuego;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ClickIzquierdo implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJuego faseJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de click izquierdo para una ejecución del Monopoly
     *
     * @param faseJuego ejecución del juego asociada
     */
    public ClickIzquierdo(FaseJuego faseJuego) {

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
        if (getFaseJuego().getMenuGUI().contienePosicion(x, y)) {

            // Se actúa en caso de que el botón presionado sea el primario (izquierdo)
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                getFaseJuego().getMenuGUI().handleClickIzquierdo(x, y);
            }
        }

        // Si la sección de información ha sido pulsada
        else if (getFaseJuego().getInformacion().contienePosicion(x, y)) {

            // Si se ha pulsado el botón primario
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                // Se comprueba que no haya ningún Input activo
                if(!getEjecucionJuego().getMenuGUI().isInputActivo()) {
                    getEjecucionJuego().getInformacion().handleClickIzquierdo(x, y);
                }
            }

            // Si se ha pulsado el botón secundario
            else if (e.getButton().equals(MouseButton.SECONDARY)) {
                getFaseJuego().getInformacion().handleClickDerecho(x, y, getFaseJuego().getRaiz(), e,
                        getFaseJuego().getMenus(), getFaseJuego().getApp());
            }
        }
    }
}
