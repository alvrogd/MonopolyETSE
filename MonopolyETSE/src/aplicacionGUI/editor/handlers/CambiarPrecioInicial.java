package aplicacionGUI.editor.handlers;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.Celda;
import aplicacionGUI.input.ILectorEntero;
import aplicacionGUI.input.InputEntero;
import javafx.event.ActionEvent;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class CambiarPrecioInicial extends CambiarAtributo implements ILectorEntero {

    /* Constructor */

    /**
     * Se crea un handler para el cambio del precio inicial de la propiedad contenida en la celda dada
     *
     * @param celda celda a asociar
     */
    public CambiarPrecioInicial(Celda celda) {

        super(celda);
    }



    /* Métodos */

    /**
     * Se cambia el precio inicial de la propiedad de la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se crea un lector de enteros
        new InputEntero(true, 0, this, ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN,
                ConstantesGUI.INPUT_ENTERO_DINERO_IMAGEN_OSCURA);
    }

    /**
     * Se almacena un entero dado como precio inicial de la casilla asociada
     *
     * @param enteroLeido           entero leído del usuario
     * @param identificadorAtributo identificador del atributo a modificar
     */
    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {

        // Se obtiene la propiedad de la celda asociada
        final Propiedad propiedad = (Propiedad) getCelda().getCasillaGUI().getCasilla();

        // El alquiler debe ser multiplicado por el tamaño del grupo dado que el de los solares es dependiente
        // de este y de su tamaño
        if (propiedad instanceof Solar) {
            propiedad.getGrupo().setPrecio(enteroLeido * propiedad.getGrupo().getTipo().getTamano());
        } else {
            propiedad.getGrupo().setPrecio(enteroLeido);
        }
    }
}
