package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class CambiarAtributo implements EventHandler<ActionEvent> {

    /* Atributos */

    // Celda asociada al handler
    private final Celda celda;



    /* Constructor */

    /**
     * Se crea un handler para el cambio de un atributo de la casilla contenida en la celda dada
     *
     * @param celda celda a asociar
     */
    public CambiarAtributo(Celda celda) {

        if (celda == null) {
            System.out.println("Celda no inicializada");
            System.exit(1);
        }

        this.celda = celda;
    }



    /* Getters y setters */

    public Celda getCelda() {
        return celda;
    }
}
