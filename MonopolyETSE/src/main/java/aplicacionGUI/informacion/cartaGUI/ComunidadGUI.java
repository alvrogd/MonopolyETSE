
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;

public class ComunidadGUI extends CartaGUI {

    /* Constructor */

    /**
     * Se crea una representación de una carta de comunidad
     *
     * @param raiz nodo sobre el cual crear un hijo para la carta
     */
    public ComunidadGUI(Group raiz) {

        // Se crea la representación de la carta
        super(raiz, ConstantesGUI.CARTA_COMUNIDAD, ConstantesGUI.COMUNIDAD_DESPLAZAMIENTO_X,
                ConstantesGUI.COMUNIDAD_DESPLAZAMIENTO_Y);
    }
}
