package aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.FaseJuego;
import aplicacionGUI.input.Input;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Ejecución del juego asociada
    private final FaseJuego faseJuego;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de releases del ratón para una ejecución del Monopoly
     *
     * @param faseJuego ejecución del juego asociada
     */
    public Release(FaseJuego faseJuego) {

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
     * Se actúa ejecutando las acciones predefinidas ante un release del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones de la pulsación anterior al release (para conocer la posición donde se comenzó a presionar el
        // botón)
        double x = getFaseJuego().getxPresionado();
        double y = getFaseJuego().getyPresionado();

        // Si se ha pulsado la sección de controles
        if (getFaseJuego().getMenuGUI().contienePosicion(x, y)) {
            getFaseJuego().getMenuGUI().handleClickSoltado(x, y);
        }

        // todo residuos?
        /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
            informacion.handleClickDerecho(xPresionado[0], yPresionado[0], raiz, e, menus);
        }*/

        /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
            informacion.handleClickIzquierdo(xPresionado[0], yPresionado[0]);
        }*/

        final ArrayList<Input> inputsActivos = getFaseJuego().getInputsActivos();
        // Si existe algún input activo
        if (inputsActivos.size() > 0) {

            // Si el primer input contenido contiene la posición en la cual se pulsó el botón del ratón
            if (inputsActivos.get(0).contienePosicion(x, y)) {
                inputsActivos.get(0).handleRelease();
            }
        }
    }
}
