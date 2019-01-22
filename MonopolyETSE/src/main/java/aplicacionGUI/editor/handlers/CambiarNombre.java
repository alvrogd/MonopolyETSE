package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import aplicacionGUI.input.ILectorString;
import aplicacionGUI.input.InputString;
import javafx.event.ActionEvent;

public class CambiarNombre extends CambiarAtributo implements ILectorString {

    /* Constructor */

    /**
     * Se crea un handler para el cambio del nombre de la casilla contenida en la celda dada
     *
     * @param celda celda a asociar
     */
    public CambiarNombre(Celda celda) {

        super(celda);
    }



    /* Métodos */

    /**
     * Se cambia el nombre de la casilla de la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se crea un lector de strings
        new InputString(true, 0, this);
    }

    /**
     * Se almacena un string dado como nombre de la casilla asociada
     *
     * @param stringLeido           string leído del usuario
     * @param identificadorAtributo identificador del atributo a modificar
     */
    @Override
    public void almacenarString(String stringLeido, int identificadorAtributo) {

        getCelda().getCasillaGUI().getCasilla().setNombre(stringLeido);
    }
}
