package aplicacionGUI.informacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.cartaGUI.SuerteGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.Tablero;

public class Informacion {

    /* Atributos */
    
    // Nodo propiedad de esta sección (la superior, representa información del juego)
    private final Group nodo;
    
    // Representación del tablero
    private final TableroGUI tableroGUI;
    
    // Representación de las cartas de suerte
    private final SuerteGUI suerteGUI;
    
    // Sensor asociado a esta sección
    private final Rectangle sensor;
    

    
    /* Constructor */
    
    public Informacion(Group raiz, Tablero tablero) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para esta región de la GUI
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);
        
        // Se establece para la región la posición correspondiente en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X,
                ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y));
        
        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.INFORMACION_ANCHO, ConstantesGUI.INFORMACION_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crea la representación del tablero
        this.tableroGUI = new TableroGUI(this.nodo, tablero);
        
        // Se crea la representación de las cartas de suerte
        this.suerteGUI = new SuerteGUI(this.nodo);
    }

    
    
    /* Getters */

    public Group getNodo() {
        return nodo;
    }

    
    public Rectangle getSensor() {
        return sensor;
    }

    
    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    
    public SuerteGUI getSuerteGUI() {
        return suerteGUI;
    }
    
    
    
    /* Métodos */
    
    public boolean contieneClickDerecho(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        return(getSensor().contains(posicionX, posicionY));
    }
    
    public void handleClickDerecho(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        if( getTableroGUI().contieneClickDerecho(posicionX, posicionY)) {
            getTableroGUI().handleClickDerecho(posicionX, posicionY);
        }
        
    }
    
    public void render() {

        getTableroGUI().render();
        getSuerteGUI().render();
    }
}
