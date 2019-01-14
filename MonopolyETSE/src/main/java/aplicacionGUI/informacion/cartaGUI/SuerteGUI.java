
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.transform.Translate;

public class SuerteGUI extends CartaGUI {
    
    /* Constructor */
    
    public SuerteGUI( Group raiz ) {
        
        // Se crea la representación de la carta
        super( raiz, ConstantesGUI.CARTA_SUERTE );
        
        // Se mueve la carta a su posición adecuada (interior del tablero, izquierda)
        super.getNodo().getTransforms().add(new Translate(ConstantesGUI.SUERTE_DESPLAZAMIENTO_X,
                ConstantesGUI.SUERTE_DESPLAZAMIENTO_Y));
    }
}
