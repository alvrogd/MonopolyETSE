package aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers;

import aplicacion.Aplicacion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class DeshipotecarPropiedad implements EventHandler<ActionEvent> {

    /* Atributos */

    // Propiedad asociada
    private final Propiedad propiedad;

    // Aplicación sobre la cual se ejecuta el juego
    private final Aplicacion app;



    /* Constructor */

    /**
     * Se crea un handler para deshipotecar la propiedad dada
     *
     * @param propiedad propiedad a deshipotecar
     * @param app       aplicación sobre la cual se ejecuta el juego
     */
    public DeshipotecarPropiedad(Propiedad propiedad, Aplicacion app) {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (app == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        this.propiedad = propiedad;
        this.app = app;
    }



    /* Getters y setters */

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public Aplicacion getApp() {
        return app;
    }



    /* Métodos */

    /**
     * Se deshipoteca la propiedad asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        try {
            getApp().getJuego().getTurno().deshipotecar(getPropiedad());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
