package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;

import java.util.Scanner;

public class CambiarNombre extends CambiarAtributo {

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

        // Se lee el nombre y se guarda
        Scanner scanner = new Scanner(System.in);
        getCelda().getCasillaGUI().getCasilla().setNombre(scanner.nextLine());
    }
}
