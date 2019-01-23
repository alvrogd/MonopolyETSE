package aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.FaseSeleccion;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

public class Pulsacion implements EventHandler<MouseEvent> {

    /* Atributos */

    // Fase de selección asociada
    private final FaseSeleccion faseSeleccion;



    /* Constructor */

    /**
     * Se crea un gestor de eventos de pulsaciones del ratón para la fase de selección de tablero
     *
     * @param faseSeleccion fase de selección asociada
     */
    public Pulsacion(FaseSeleccion faseSeleccion) {

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
     * Se actúa ejecutando las acciones predefinidas ante una pulsación del ratón
     *
     * @param e evento de ratón que causa la ejecución
     */
    @Override
    public void handle(MouseEvent e) {

        // Posiciones del click
        double x = e.getX();
        double y = e.getY();

        // Se almacenan
        getFaseSeleccion().setxPresionado(x);
        getFaseSeleccion().setyPresionado(y);

        // Se cambia la imagen de fondo si alguno de los sensores contiene el click
        if(getFaseSeleccion().getSensorEditor().contains(x, y)) {

            getFaseSeleccion().setFondoSeleccionado(getFaseSeleccion().getEditorSeleccionado());

            MediaPlayer reproductor = new MediaPlayer(getFaseSeleccion().getSonido());
            reproductor.play();
        }

        else if(getFaseSeleccion().getSensorPredeterminado().contains(x, y)) {

            getFaseSeleccion().setFondoSeleccionado(getFaseSeleccion().getPredeterminadoSeleccionado());

            MediaPlayer reproductor = new MediaPlayer(getFaseSeleccion().getSonido());
            reproductor.play();
        }
    }
}
