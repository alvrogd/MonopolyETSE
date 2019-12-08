package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.FaseJugador;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJugador faseJugador;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de releases del ratón para una ejecución del Monopoly
     *
     * @param faseJugador ejecución del juego asociada
     */
    public Release(FaseJugador faseJugador) {

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
     * Se actúa ejecutando las acciones predefinidas ante un release del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones de la pulsación anterior al release (para conocer la posición donde se comenzó a presionar el
        // botón)
        double x = getFaseJugador().getxPresionado();
        double y = getFaseJugador().getyPresionado();

        if(getFaseJugador().isIniciado()){
            getFaseJugador().handlerRelease(x, y);
        }

        final ArrayList<Input> inputsActivos = getFaseJugador().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido contiene la posición en la cual se pulsó el botón del ratón
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handleRelease();
            }
        }
    }
}
