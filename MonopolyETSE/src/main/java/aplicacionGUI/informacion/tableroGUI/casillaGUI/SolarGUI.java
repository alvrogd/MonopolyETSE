package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
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
    
    public SolarGUI(TableroGUI tableroGUI, Group raiz, Solar solar, String ficheroFondo, int posicionX, int posicionY) {

        super(tableroGUI, raiz, solar, ficheroFondo, posicionX, posicionY);
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
    public void renderContenido() {
        
        super.renderContenido();
        renderEdificiosContenidos();
    }
    
    
    public void renderEdificiosContenidos() {
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);
        
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
                
                getGc().drawImage( imagen, 4 + desplazamiento, 37 );
                desplazamiento += 10;
            }
        }
    }
}
