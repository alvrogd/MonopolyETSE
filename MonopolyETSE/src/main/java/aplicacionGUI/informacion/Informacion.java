package aplicacionGUI.informacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.cartaGUI.ComunidadGUI;
import aplicacionGUI.informacion.cartaGUI.SuerteGUI;
import aplicacionGUI.informacion.marcoInformacion.MarcoInformacion;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
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
    
    // Representación de las cartas de comunidad
    private final ComunidadGUI comunidadGUI;
    
    // Marco de información
    private final MarcoInformacion marcoInformacion;
    
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
        
        // Se crea la representación de las cartas de comunidad
        this.comunidadGUI = new ComunidadGUI(this.nodo);
        
        // Se crea el marco de información
        this.marcoInformacion = new MarcoInformacion(this.nodo);
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

    
    public ComunidadGUI getComunidadGUI() {
        return comunidadGUI;
    }

    public MarcoInformacion getMarcoInformacion() {
        return marcoInformacion;
    }
    
    
    
    
    
    /* Métodos */
    
    public boolean contienePosicion(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        return(getSensor().contains(posicionX, posicionY));
    }
    
    
    public void handleClickIzquierdo(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        if( getSuerteGUI().contienePosicion(posicionX, posicionY)) {
            getSuerteGUI().handleClickIzquierdo(posicionX, posicionY);
        }
        
        else if( getComunidadGUI().contienePosicion(posicionX, posicionY)) {
            getComunidadGUI().handleClickIzquierdo(posicionX, posicionY);
        }
        
        else if(getMarcoInformacion().contienePosicion(posicionX, posicionY)) {
            getMarcoInformacion().handleClickIzquierdo(posicionX, posicionY);
        }
    }
    
    
    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        if( getTableroGUI().contienePosicion(posicionX, posicionY)) {
            getTableroGUI().handleClickDerecho(posicionX, posicionY, nodoRaiz, e, menus);
        }   
    }
    
    
    public void render(double t) {

        getTableroGUI().render(t);
        getSuerteGUI().render();
        getComunidadGUI().render();
        getMarcoInformacion().render(t);
    }
}
