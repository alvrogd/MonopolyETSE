
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.Informacion;
import javafx.scene.Group;

public class ComunidadGUI extends CartaGUI {

    /* Constructor */

    /**
     * Se crea una representación de una carta de comunidad
     *
     * @param raiz nodo sobre el cual crear un hijo para la carta
     */
    public ComunidadGUI(Group raiz, Informacion informacion) {

        // Se crea la representación de la carta
        super(raiz, ConstantesGUI.COMUNIDAD_BARAJA_DESPLAZAMIENTO_X, ConstantesGUI.COMUNIDAD_BARAJA_DESPLAZAMIENTO_Y,
                ConstantesGUI.BARAJA_COMUNIDAD, ConstantesGUI.VOLTEO_COMUNIDAD,
                ConstantesGUI.CARTA_COMUNIDAD_DESPLAZAMIENTO_X, ConstantesGUI.CARTA_COMUNIDAD_DESPLAZAMIENTO_Y, informacion);
    }
}
