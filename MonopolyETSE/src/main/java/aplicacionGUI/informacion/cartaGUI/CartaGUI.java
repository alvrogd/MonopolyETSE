
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import resources.casillas.FondosCasillas;

public abstract class CartaGUI {
    
    /* Atributos */
    
    // Nodo propiedad del tablero
    private final Group nodo;
    
    // Canvas contenido en el nodo
    private final Canvas canvas;
    
    // Contexto en el que representar objetos
    private final GraphicsContext gc;
    
    // Imagen de la carta
    private final Image imagen;
    
    
    
    
    /* Constructor */
    
    public CartaGUI( Group raiz, String imagen ) {
        
        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }
        
        if( imagen == null ) {
            System.err.println("Nombre de la imagen no inicializado");
            System.exit(1);
        }
        
        // Se añade al nodo dado un nuevo nodo de uso para la carta
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        // Se crea un canvas en el nuevo nodo para representar la carta
        this.canvas = new Canvas( ConstantesGUI.CARTA_ANCHO, ConstantesGUI.CARTA_ALTO);
        this.nodo.getChildren().add(canvas);
        
        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        this.gc = this.canvas.getGraphicsContext2D();
        
        // Se obtiene la imagen correspondiente
        this.imagen = new Image(FondosCasillas.class.getResource(imagen).toString());
    }  
    
    
    
    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }

    
    public Canvas getCanvas() {
        return canvas;
    }

    
    public GraphicsContext getGc() {
        return gc;
    }

    
    public Image getImagen() {
        return imagen;
    }
    
    
    
    
    
    /* Métodos */
    
    public void render() {
        
        // Se muestra la imagen
        gc.drawImage(getImagen(), 0, 0);
    }
}
