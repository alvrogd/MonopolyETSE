
package aplicacionGUI.informacion.marcoInformacion;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import resources.marcoInformacion.ImagenesMarcoInformacion;

public class MarcoInformacion {
 
    /* Atributos */
    
    // Nodo propiedad de la casilla
    private final Group nodo;
    
    // Canvas contenido en el nodo
    private Canvas canvas;
    
    // Contexto en el que representar objetos
    private GraphicsContext gc;
    
    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.MARCO_INFORMACION_ANCHO;
    private int alto;
    
    // Imágenes correspondientes al marco
    private final static Image SECCION_SUPERIOR = new Image( ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_SUPERIOR).toString());
    
    private final static Image SECCION_CENTRAL = new Image( ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_CENTRAL).toString());
    
    private final static Image SECCION_INFERIOR = new Image( ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_INFERIOR).toString());
    
    // Información a representar
    private String[] informacion;
    
    // Nümero de secciones centrales necesarias
    private int numeroSeccionesCentrales;
    
    // Boolean si se encuentra activo
    private boolean activo;
    
    
    /* Constructor */
    
    public MarcoInformacion(Group raiz){
        
        if(raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }
        
        // Se añade al nodo dado un nuevo nodo de uso para el marco
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        this.activo = false;
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

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public static Image getSECCION_SUPERIOR() {
        return SECCION_SUPERIOR;
    }

    public static Image getSECCION_CENTRAL() {
        return SECCION_CENTRAL;
    }

    public static Image getSECCION_INFERIOR() {
        return SECCION_INFERIOR;
    }

    public String[] getInformacion() {
        return informacion;
    }

    public void setInformacion(String[] informacion) {
        this.informacion = informacion;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getNumeroSeccionesCentrales() {
        return numeroSeccionesCentrales;
    }

    public void setNumeroSeccionesCentrales(int numeroSeccionesCentrales) {
        this.numeroSeccionesCentrales = numeroSeccionesCentrales;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    /* Métodos */
    
    public void actualizarContenido(String[] informacion) {
        
        // Se obtiene la cantidad de secciones centrales necesarias para representar la información
        setNumeroSeccionesCentrales(calcularSeccionesCentrales(informacion));
        
        // Se establece el tamaño vertical total
        setAlto(ConstantesGUI.MARCO_INFORMACION_SUPERIOR_ALTO + getNumeroSeccionesCentrales() *
                ConstantesGUI.MARCO_INFORMACION_CENTRAL_ALTO + ConstantesGUI.MARCO_INFORMACION_INFERIOR_ALTO);
        
        // Se mueve el marco a su posición adecuada
        getNodo().getTransforms().clear();
        getNodo().getTransforms().add(new Translate(ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X,
                (double)(ConstantesGUI.TABLERO_ALTO - getAlto()) / 2 - 18));
        
        // Se crea un canvas en el nodo para representar el marco
        getNodo().getChildren().remove(getCanvas());
        setCanvas(new Canvas( ConstantesGUI.MARCO_INFORMACION_ANCHO, getAlto()));
        getNodo().getChildren().add(canvas);
        
        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        setGc(getCanvas().getGraphicsContext2D());
        
        // Se guarda la información a representar
        setInformacion(informacion);
    }
    
    private int calcularSeccionesCentrales(String[] informacion) {
        
        return( informacion.length);
    }
    
    public void render() {
        
        int desplazamiento = 0;
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 16));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);
        
        // Se muestra la sección superior
        getGc().drawImage(getSECCION_SUPERIOR(), 0, 0);
        desplazamiento += ConstantesGUI.MARCO_INFORMACION_SUPERIOR_ALTO;
        
        // Se muestran las secciones centrales junto con el texto
        for( int i = 0; i < getNumeroSeccionesCentrales(); i++ ) {
            
            getGc().drawImage(getSECCION_CENTRAL(), -2, desplazamiento);     
            getGc().fillText(getInformacion()[i], getAncho()/2, desplazamiento + 10);
            
            desplazamiento += ConstantesGUI.MARCO_INFORMACION_CENTRAL_ALTO;
        }
        
        // Se muestra la sección inferior
        getGc().drawImage(getSECCION_INFERIOR(), 0, desplazamiento);
    }
}
