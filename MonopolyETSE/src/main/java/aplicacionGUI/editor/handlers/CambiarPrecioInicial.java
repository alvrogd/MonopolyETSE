package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

import java.util.Scanner;

public class CambiarPrecioInicial extends CambiarAtributo {

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

        // Se crea un scanner para el input del usuario
        Scanner scanner = new Scanner(System.in);

        // Se obtiene la propiedad de la celda asociada
        final Propiedad propiedad = (Propiedad) getCelda().getCasillaGUI().getCasilla();

        // El alquiler debe ser multiplicado por el tamaño del grupo dado que el de los solares es dependiente
        // de este y de su tamaño
        if (propiedad instanceof Solar) {
            propiedad.getGrupo().setPrecio(scanner.nextInt() * propiedad.getGrupo().getTipo().getTamano());
        } else {
            propiedad.getGrupo().setPrecio(scanner.nextInt());
        }
    }
}
