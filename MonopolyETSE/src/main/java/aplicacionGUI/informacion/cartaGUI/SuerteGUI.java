
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;

public class SuerteGUI extends CartaGUI {
    
    /* Constructor */
    
    public SuerteGUI( Group raiz ) {
        
        // Se crea la representación de la carta
        super( raiz, ConstantesGUI.CARTA_SUERTE, ConstantesGUI.SUERTE_DESPLAZAMIENTO_X,
                ConstantesGUI.SUERTE_DESPLAZAMIENTO_Y );
    }
}
