package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Banca;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class PropiedadGUI extends CasillaGUI {

    /* Constructor */
    
    public PropiedadGUI(Group raiz, Propiedad propiedad, String ficheroFondo, int posicionX, int posicionY) {

        super(raiz, propiedad, ficheroFondo, posicionX, posicionY);
    }

    
    
    /* Métodos */
    
    public Propiedad getPropiedad() {

        return ((Propiedad) getCasilla());
    }
    
    
    @Override
    public void renderContenido() {
        
        super.renderContenido();
        renderPropietario();
    }
    
    
    public void renderPropietario() {
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);
        
        // Si el propietario es la banca, se añade el precio de la casilla
        if( getPropiedad().getPropietario() instanceof Banca ) {
            
            getGc().fillText(getPropiedad().getPrecioActual()+ " K €", 5, 58 );
        }
        
        // En caso contrario, se indica el propietario
        else {
            
            // Se diferencia entre estar hipotecada o no
            if( getPropiedad().isHipotecada() ) {
                
                getGc().fillText("Hipot.: " + getPropiedad().getPropietario().getNombre(), 5, 58 );
            }
            
            else {
                
                getGc().fillText("Prop.: " + getPropiedad().getPropietario().getNombre(), 5, 58 );
            }     
        }
    }
}
