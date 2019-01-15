package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import resources.edificios.ImagenesEdificios;

public class SolarGUI extends PropiedadGUI {
    
    /* Atributos */
    
    public static final Image CASA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.CASA).toString());
    public static final Image HOTEL = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.HOTEL).toString());
    public static final Image PISCINA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.PISCINA).toString());
    public static final Image PISTA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.PISTA).toString());

    
    
    /* Constructor */
    
    public SolarGUI(Solar solar, String ficheroFondo) {

        super(solar, ficheroFondo);
    }

    
    
    /* Getters y setters */
    
    public Solar getSolar() {

        return ((Solar) getCasilla());
    }

    
    public static Image getCASA() {
        return CASA;
    }

    
    public static Image getHOTEL() {
        return HOTEL;
    }

    
    public static Image getPISCINA() {
        return PISCINA;
    }

    
    public static Image getPISTA() {
        return PISTA;
    }
    
    
    
    /* Métodos */
    
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
            
            // Se obtiene la imagen apropiada para el tipo de edificio iterado
            Image imagen = null;
            
            switch( tipoEdificio ) {
                
                case casa:
                    imagen = getCASA();
                    break;
                    
                case hotel:
                    imagen = getHOTEL();
                    break;
                    
                case piscina:
                    imagen = getPISCINA();
                    break;
                    
                case pistaDeporte:
                    imagen = getPISTA();
                    break;
            }
            
            // Se añade un identificador por cada edificio contenido del tipo iterado
            for( int i = 0; i < numeroEdificios; i++ ) {
                
                gc.drawImage( imagen, x + 4 + desplazamiento, y + 37 );
                desplazamiento += 10;
            }
        }
    }
}
