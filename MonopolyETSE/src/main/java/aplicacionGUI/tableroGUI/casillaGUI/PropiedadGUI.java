package aplicacionGUI.tableroGUI.casillaGUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Banca;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class PropiedadGUI extends CasillaGUI {

    /* Constructor */
    
    public PropiedadGUI(Propiedad propiedad, String ficheroFondo) {

        super(propiedad, ficheroFondo);
    }

    
    
    /* Métodos */
    
    public Propiedad getPropiedad() {

        return ((Propiedad) getCasilla());
    }
    
    @Override
    public void renderContenido(GraphicsContext gc ) {
        
        super.renderContenido(gc);
        renderPropietario(gc);
    }
    
    public void renderPropietario(GraphicsContext gc) {
        
        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        
        // Si el propietario es la banca, se añade el precio de la casilla
        if( getPropiedad().getPropietario() instanceof Banca ) {
            
            gc.fillText(getPropiedad().getImporteCompra() + " K €", 5, 55 );
        }
        
        // En caso contrario, se indica el propietario
        else {
            
            // Se diferencia entre estar hipotecada o no
            if( getPropiedad().isHipotecada() ) {
                
                gc.fillText("Hipot.: " + getPropiedad().getPropietario().getNombre(), 5, 55 );
            }
            
            else {
                
                gc.fillText("Prop.: " + getPropiedad().getPropietario().getNombre(), 5, 55 );
            }     
        }
    }
}
