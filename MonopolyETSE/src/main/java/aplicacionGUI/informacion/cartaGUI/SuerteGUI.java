
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.Informacion;
import javafx.scene.Group;

public class SuerteGUI extends CartaGUI {

    /* Constructor */

    /**
     * Se crea una representación de una carta de suerte
     *
     * @param raiz nodo sobre el cual crear un hijo para la carta
     */
    public SuerteGUI(Group raiz, Informacion informacion) {

        // Se crea la representación de la carta
        super(raiz, ConstantesGUI.SUERTE_BARAJA_DESPLAZAMIENTO_X, ConstantesGUI.SUERTE_BARAJA_DESPLAZAMIENTO_Y,
                ConstantesGUI.BARAJA_SUERTE, ConstantesGUI.VOLTEO_SUERTE,
                ConstantesGUI.CARTA_SUERTE_DESPLAZAMIENTO_X, ConstantesGUI.CARTA_SUERTE_DESPLAZAMIENTO_Y, informacion);
    }
}
