package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.FaseJugador;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJugador faseJugador;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para una ejecución del Monopoly
     *
     * @param faseJugador ejecución del juego asociada
     */
    public Pulsacion(FaseJugador faseJugador) {

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
        getFaseJugador().setxPresionado(x);
        getFaseJugador().setyPresionado(y);

        if(getFaseJugador().isIniciado()){
            getFaseJugador().handlerPulsado(x, y);
        }

    }
}
