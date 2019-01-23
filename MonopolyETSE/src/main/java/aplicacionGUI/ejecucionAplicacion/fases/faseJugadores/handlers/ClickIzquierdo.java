package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.FaseJugador;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import monopoly.Juego;

public class ClickIzquierdo implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJugador faseJugador;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de click izquierdo para una ejecución del Monopoly
     *
     * @param faseJugador ejecución del juego asociada
     */
    public ClickIzquierdo(FaseJugador faseJugador) {

        if (faseJugador == null) {
            System.err.println("Ejecución del juego no inicializada");
            System.exit(1);
        }

        this.faseJugador = faseJugador;
    }



    /* Getters y setters */

    public FaseJugador getFaseJugador() {
        return faseJugador;
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

        // Se comprueba si la fase está activa
        if(getFaseJugador().isIniciado()){
            getFaseJugador().handlerIzquierdo(x, y);
        }

    }
}
