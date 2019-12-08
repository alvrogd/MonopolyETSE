package aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.handlers;

import aplicacionGUI.ejecucionAplicacion.TipoFase;
import aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.FaseSeleccion;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Release implements EventHandler<MouseEvent> {

    /* Atributos */

    // Fase de selección asociada
    private final FaseSeleccion faseSeleccion;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de releases del ratón para la fase de selección de tablero
     *
     * @param faseSeleccion fase de selección asociada
     */
    public Release(FaseSeleccion faseSeleccion) {

        if (faseSeleccion == null) {
            System.err.println("Fase de selección no inicializada");
            System.exit(1);
        }

        this.faseSeleccion = faseSeleccion;
    }



    /* Getters y setters */

    public FaseSeleccion getFaseSeleccion() {
        return faseSeleccion;
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
        double x = getFaseSeleccion().getxPresionado();
        double y = getFaseSeleccion().getyPresionado();

        // Se cambia la imagen de fondo a la de por defecto si alguno de los sensores contiene el click
        if(getFaseSeleccion().getSensorEditor().contains(x, y)) {
            getFaseSeleccion().getAplicacionGUI().setTipoFase(TipoFase.creacionTablero);
            getFaseSeleccion().setFondoSeleccionado(getFaseSeleccion().getFondo());

            getFaseSeleccion().getAplicacionGUI().ejecutarFase(getFaseSeleccion().getAplicacionGUI().getTipoFase());
        }

        else if(getFaseSeleccion().getSensorPredeterminado().contains(x, y)) {
            getFaseSeleccion().getAplicacionGUI().setTipoFase(TipoFase.inicioJuego);
            getFaseSeleccion().setFondoSeleccionado(getFaseSeleccion().getFondo());

            getFaseSeleccion().getAplicacionGUI().ejecutarFase(getFaseSeleccion().getAplicacionGUI().getTipoFase());
        }
    }
}
