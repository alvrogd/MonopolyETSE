package aplicacionGUI.tableroGUI.casillaGUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

public class SolarGUI extends PropiedadGUI {

    /* Constructor */
    
    public SolarGUI(Solar solar, String ficheroFondo) {

        super(solar, ficheroFondo);
    }

    
    
    /* Métodos */
    
    public Solar getSolar() {

        return ((Solar) getCasilla());
    }
    
    @Override
    public void renderContenido(GraphicsContext gc, int x, int y) {
        
        super.renderContenido(gc, x, y);
        renderEdificiosContenidos(gc, x, y);
    }
    
    public void renderEdificiosContenidos(GraphicsContext gc, int x, int y) {
        
        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);
        
        // Se añaden los edificos contenidos
        int desplazamiento = 0;
        
        // Para cada tipo de edificio
        for (TipoEdificio tipoEdificio : getSolar().getEdificiosContenidos().keySet()) {
            
            // El número de edificios contenidos del tipo iterado
            int numeroEdificios = getSolar().getEdificiosContenidos().get(tipoEdificio).size();
            
            // Se obtiene el identificador apropiado para el tipo de edificio iterado
            String identificador = null;
            
            switch( tipoEdificio ) {
                
                case casa:
                    identificador = "c";
                    break;
                    
                case hotel:
                    identificador = "h";
                    break;
                    
                case piscina:
                    identificador = "pc";
                    break;
                    
                case pistaDeporte:
                    identificador = "pt";
                    break;
            }
            
            // Se añade un identificador por cada edificio contenido del tipo iterado
            for( int i = 0; i < numeroEdificios; i++ ) {
                
                gc.fillText(identificador, x + 4 + desplazamiento, y + 35);
                desplazamiento += 10;
            }
        }
    }
}
