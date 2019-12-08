package aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers;

import aplicacion.Aplicacion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

public class VenderEdificio implements EventHandler<ActionEvent> {

    /* Atributos */

    // Solar asociado
    private final Solar solar;

    // Tipo de edificio a vender
    private final TipoEdificio tipoEdificio;

    // Aplicación sobre la cual se ejecuta el juego
    private final Aplicacion app;



    /* Constructor */

    /**
     * Se crea un handler para vender un tipo de edificio dado de un solar especificado
     *
     * @param solar    solar cuyos edificios vender
     * @param tipoEdificio tipo de edificio a vender
     * @param app          aplicación sobre la cual se ejecuta el juego
     */
    public VenderEdificio(Solar solar, TipoEdificio tipoEdificio, Aplicacion app) {

        if (solar == null) {
            System.err.println("Solar no inicializada");
            System.exit(1);
        }

        if( tipoEdificio == null ) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (app == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        this.solar = solar;
        this.tipoEdificio = tipoEdificio;
        this.app = app;
    }



    /* Getters y setters */

    public Solar getSolar() {
        return solar;
    }

    public TipoEdificio getTipoEdificio() {
        return tipoEdificio;
    }

    public Aplicacion getApp() {
        return app;
    }



    /* Métodos */

    /**
     * Se vende un edificio del tipo dado en la casilla asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        try {
            getApp().getJuego().getTurno().venderEdificio(getTipoEdificio(), 1, getSolar());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
