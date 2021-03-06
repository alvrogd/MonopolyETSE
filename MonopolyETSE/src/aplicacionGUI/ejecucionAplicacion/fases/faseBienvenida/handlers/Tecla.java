package aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida.handlers;

import aplicacionGUI.ejecucionAplicacion.TipoFase;
import aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida.FaseBienvenida;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Tecla implements EventHandler<KeyEvent> {

    /* Atributos */

    // Fase de bienvenida asociada
    private final FaseBienvenida faseBienvenida;



    /* Constructor */

    /**
     * Se crea un gestor para las pulsaciones de teclas en la fase de bienvenida
     *
     * @param faseBienvenida fase de bienvenida a asociar
     */
    public Tecla(FaseBienvenida faseBienvenida) {

        if (faseBienvenida == null) {
            System.err.println("Fase de bienvenida no inicializada");
            System.exit(1);
        }

        this.faseBienvenida = faseBienvenida;
    }



    /* Getters y setters */

    public FaseBienvenida getFaseBienvenida() {
        return faseBienvenida;
    }



    /* Métodos */

    @Override
    public void handle(KeyEvent e) {

        if( e.isControlDown() ) {

            // Si se presiona Ctrl+P, se intercambia el fondo
            if(e.getCode().equals(KeyCode.P)) {

                if( getFaseBienvenida().getFondoSeleccionado().equals(getFaseBienvenida().getFondo())) {
                    getFaseBienvenida().setFondoSeleccionado(getFaseBienvenida().getFondoAlternativo());
                }

                else {
                    getFaseBienvenida().setFondoSeleccionado(getFaseBienvenida().getFondo());
                }
            }

        }

        else {

            // Se inicia la siguiente fase
            getFaseBienvenida().getAplicacionGUI().setTipoFase(TipoFase.introduccionJugadores);
            getFaseBienvenida().getAplicacionGUI().ejecutarFase(getFaseBienvenida().getAplicacionGUI().getTipoFase());
        }
    }
}
