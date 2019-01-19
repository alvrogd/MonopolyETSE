
package aplicacionGUI;

import java.util.ArrayList;
import javafx.scene.image.Image;


public class ImagenAnimada {
    
    /* Atributos */
    
    // Sprites de la animación
    private final ArrayList<Image> frames;
    
    // Duración de la animación
    private double duracion;
    
    // Tiempo de inicio de la animación
    private double tiempoInicio;
    
    
    
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
        
        this.tiempoInicio = 0;
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

    public double getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(double tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }
    
    
    
    
    /* Métodos */
    
    public Image getFrame(double t) {
        
        int i = (int)(((t - getTiempoInicio())% (getFrames().size() * getDuracion())) / getDuracion());
        
        return( getFrames().get(i));
    }
    
    public Image getFrameInverso(double t) {
        
        int i = (int)(((t - getTiempoInicio())% (getFrames().size() * getDuracion())) / getDuracion());
        
        return( getFrames().get(getFrames().size() - 1 - i));
    }
    
    public Image getFrameNumero(int i) {
                
        return( getFrames().get(i));
    }
    
    public Image getFrameInversoNumero(int i) {
                
        return( getFrames().get(getFrames().size() - 1 - i));
    }
}
