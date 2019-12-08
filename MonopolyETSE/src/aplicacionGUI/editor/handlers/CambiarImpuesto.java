package aplicacionGUI.editor.handlers;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.Celda;
import aplicacionGUI.input.ILectorEntero;
import aplicacionGUI.input.InputEntero;
import javafx.event.ActionEvent;
import monopoly.tablero.jerarquiaCasillas.Impuesto;

public class CambiarImpuesto extends CambiarAtributo implements ILectorEntero {

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
        // Se crea un lector de enteros
        new InputEntero(true, 0, this, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN,
                ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN_OSCURA);
    }

    /**
     * Se almacena un entero dado como impuesto de la casilla asociada
     *
     * @param enteroLeido           entero leído del usuario
     * @param identificadorAtributo identificador del atributo a modificar
     */
    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {

        ((Impuesto) getCelda().getCasillaGUI().getCasilla()).setImpuesto(enteroLeido);
    }
}
