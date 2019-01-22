package aplicacionGUI.informacion;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.cartaGUI.ComunidadGUI;
import aplicacionGUI.informacion.cartaGUI.SuerteGUI;
import aplicacionGUI.informacion.marcoInformacion.MarcoInformacion;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.Tablero;

import java.util.ArrayList;

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
    
    // Sección de controles
    private MenuGUI menuGUI;



    /* Constructor */

    /**
     * Se crea una sección en la que representar un tablero, las cartas, un marco de información y el input para el
     * usuario
     * @param raiz nodo sobre el que crear un hijo para la sección de información
     * @param tablero tablero a partir del cual generar la representación de un tablero
     */
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
        this.tableroGUI = new TableroGUI(this, this.nodo, tablero);
        
        // Se crea la representación de las cartas de suerte
        this.suerteGUI = new SuerteGUI(this.nodo);
        
        // Se crea la representación de las cartas de comunidad
        this.comunidadGUI = new ComunidadGUI(this.nodo);
        
        // Se crea el marco de información
        this.marcoInformacion = new MarcoInformacion(this.nodo);
    }

    
    
    /* Getters y setters */

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

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public void setMenuGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
    }



    /* Métodos */

    /**
     * Se comprueba si contiene una posición 2D dada
     *
     * @param x coordenada X
     * @param y coordenada Y
     * @return si contiene la posición dada
     */
    public boolean contienePosicion(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        return(getSensor().contains(posicionX, posicionY));
    }

    /**
     * Se ejecuta la acción definida ante un izquierdo
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     */
    public void handleClickIzquierdo(double x, double y) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        if( getSuerteGUI().contienePosicion(posicionX, posicionY)) {
            getSuerteGUI().handleClickIzquierdo(posicionX, posicionY);
        }

        else if(getTableroGUI().contienePosicion(posicionX, posicionY)){
            getTableroGUI().handleClickIzquierdo(posicionX, posicionY);
        }
        
        else if( getComunidadGUI().contienePosicion(posicionX, posicionY)) {
            getComunidadGUI().handleClickIzquierdo(posicionX, posicionY);
        }
        
        else if(getMarcoInformacion().contienePosicion(posicionX, posicionY)) {
            getMarcoInformacion().handleClickIzquierdo(posicionX, posicionY);
        }
    }

    /**
     * Se ejecuta la acción definida ante un click
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     * @param nodoRaiz nodo de anclaje
     * @param e        evento del click
     * @param menus    conjunto de menús contextuales activos
     * @param app      aplicación de Monopoly sobre la cual se ejecuta el juego
     */
    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus, Aplicacion app) {
        
        double posicionX = x - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y;
        
        if( getTableroGUI().contienePosicion(posicionX, posicionY)) {
            getTableroGUI().handleClickDerecho(posicionX, posicionY, nodoRaiz, e, menus, app);
        }   
    }

    /**
     * Se renderiza la sección de información
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        getTableroGUI().render(t);
        getSuerteGUI().render();
        getComunidadGUI().render();

        //El marco solo se renderiza en caso de que no haya inputs activos
        if(!getMenuGUI().isInputActivo()) {
            getMarcoInformacion().render(t);
        } else {
            getMarcoInformacion().getGc().clearRect(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, ConstantesGUI.MARCO_INFORMACION_ALTO);
        }
    }
}
