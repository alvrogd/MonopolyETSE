
package aplicacionGUI;

import java.util.ArrayList;
import javafx.scene.image.Image;


public class ImagenAnimada {
    
    /* Atributos */
    
    // Sprites de la animación
    private final ArrayList<Image> frames;
    
    // Duración de la animación
    private double duracion;
    
    
    
    /* Constructor */
    
    public ImagenAnimada( Object localizador, String[] nombresFrames, double duracion ) {
        
        if( localizador == null ) {
            System.err.println("Localizador no inicalizado");
            System.exit(1);
        }
        
        if( nombresFrames == null ) {
            System.err.println("Array de nombres no inicializado");
            System.exit(1);
        }
        
        if( duracion <= 0) {
            System.err.println("La duración debe ser mayor que 0");
            System.exit(1);
        }
        
        // Se crean imágenes con los frames especificados
        this.frames = new ArrayList<>();
        
        for( String string : nombresFrames ) {
            this.frames.add(new Image(localizador.getClass().getResource(string).toString()));
        }
        
        // Se guarda la duración
        this.duracion = duracion;
    }
    
    
    
    /* Getters y setters */

    public ArrayList<Image> getFrames() {
        return frames;
    }

    
    public double getDuracion() {
        return duracion;
    }

    
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
    
    
    /* Métodos */
    
    public Image getFrame(double t) {
        
        int i = (int)((t % (getFrames().size() * getDuracion())) / getDuracion());
        
        return( getFrames().get(i));
    }
}
