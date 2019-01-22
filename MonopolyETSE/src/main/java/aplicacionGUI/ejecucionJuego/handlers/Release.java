package aplicacionGUI.ejecucionJuego.handlers;

import aplicacionGUI.ejecucionJuego.EjecucionJuego;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final EjecucionJuego ejecucionJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de releases del ratón para una ejecución del Monopoly
     *
     * @param ejecucionJuego ejecución del juego asociada
     */
    public Release(EjecucionJuego ejecucionJuego) {

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
     * Se actúa ejecutando las acciones predefinidas ante un release del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones de la pulsación anterior al release (para conocer la posición donde se comenzó a presionar el
        // botón)
        double x = getEjecucionJuego().getxPresionado();
        double y = getEjecucionJuego().getyPresionado();

        // Si se ha pulsado la sección de controles
        if (getEjecucionJuego().getMenuGUI().contienePosicion(x, y)) {
            getEjecucionJuego().getMenuGUI().handleClickSoltado(x, y);
        }

        // todo residuos?
        /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
            informacion.handleClickDerecho(xPresionado[0], yPresionado[0], raiz, e, menus);
        }*/

        /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
            informacion.handleClickIzquierdo(xPresionado[0], yPresionado[0]);
        }*/

        final ArrayList<Input> inputsActivos = getEjecucionJuego().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido contiene la posición en la cual se pulsó el botón del ratón
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handleRelease();
            }
        }
    }
}
