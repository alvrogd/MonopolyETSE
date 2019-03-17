package aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers;

import aplicacion.Aplicacion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.tablero.jerarquiaCasillas.Casilla;

public class DescribirCasilla implements EventHandler<ActionEvent> {

    /* Atributos */

    // Casilla asociada
    private final Casilla casilla;



    /* Constructor */

    /**
     * Se crea un handler para describir la casilla dada
     *
     * @param casilla casilla a describir
     */
    public DescribirCasilla(Casilla casilla) {

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        this.casilla = casilla;
    }



    /* Getters y setters */

    public Casilla getCasilla() {
        return casilla;
    }



    /* Métodos */

    /**
     * Se describe la casilla asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        Aplicacion.consola.imprimir(getCasilla().toString());
    }
}
