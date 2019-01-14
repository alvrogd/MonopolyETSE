package aplicacionGUI.tableroGUI;

import aplicacion.salidaPantalla.TipoColor;
import javafx.scene.paint.Color;

public class ColorCasillaGUI {

    /* Métodos */
    public static Color tipoColorToColorTransparente(TipoColor tipoColor) {

        if (tipoColor == null) {
            System.err.println("Tipo de color no inicializado");
            System.exit(1);
        }
        
        Color color = null;

        switch (tipoColor) {

            case resetAnsi:
            case Negrita:
                color = Color.rgb(128, 128, 128, 0.85);
                break;

            case negroANSI:
                color = Color.rgb(153, 76, 0, 0.85);
                break;
                
            case rojoANSI:
                color = Color.rgb(255, 0, 0, 0.85);
                break;
                
            case verdeANSI:
                color = Color.rgb(0, 128, 0, 0.85);
                break;
                
            case amarilloANSI:
                color = Color.rgb(255, 255, 0, 0.85);
                break;
                
            case azulANSI:
                color = Color.rgb(0, 0, 255, 0.85);
                break;
                
            case violetaANSI:
                color = Color.rgb(127, 0, 255, 0.85);
                break;
                
            case cianANSI:
                color = Color.rgb( 0, 255, 255, 0.85);
                break;
                
            case blancoANSI:
                color = Color.rgb(255, 255, 255, 0.85);
                break;
                
        }
        
        return( color );
    }
}
