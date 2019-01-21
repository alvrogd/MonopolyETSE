package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;
import monopoly.tablero.jerarquiaCasillas.Impuesto;

import java.util.Scanner;

public class CambiarImpuesto extends CambiarAtributo {

    /* Constructor */

    /**
     * Se crea un handler para el cambio del impuesto de la casilla de impuesto contenida en la celda dada
     *
     * @param celda celda a asociar
     */
    public CambiarImpuesto(Celda celda) {

        super(celda);
    }



    /* Métodos */

    /**
     * Se cambia el impuesto de la casilla de impuesto de la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se lee el nuevo impuesto y se guarda
        // todo comprobar excepciones de formato incorrecto
        // todo no se renderiza el impuesto por defecto
        Scanner scanner = new Scanner(System.in);
        final Impuesto impuesto = (Impuesto) getCelda().getCasillaGUI().getCasilla();
        impuesto.setImpuesto(scanner.nextInt());

    }
}
